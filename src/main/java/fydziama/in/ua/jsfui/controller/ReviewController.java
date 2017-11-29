package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.GoodDao;
import fydziama.in.ua.dao.ReviewDao;
import fydziama.in.ua.dao.UserDao;
import fydziama.in.ua.entity.Good;
import fydziama.in.ua.entity.Review;
import fydziama.in.ua.entity.User;
import fydziama.in.ua.jsfui.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class ReviewController  extends AbstractController<Review> {

    public static final String REVIEW_SEARCH_COLUMN = "reviewDate";
    public static final Sort.Direction REVIEW_SORT_DIRECTION= Sort.Direction.DESC;

    private static final int DEFAULT_PAGE_SIZE = 6;
    private static final int MIN_PAGE_SIZE = 6;
    private int rowsCount = DEFAULT_PAGE_SIZE;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private GoodController goodController;

    @Autowired
    private UserController userController;

    private User user;

    private Good selectedGood;
    private long selectedGoodId;

    private Review selectedReview;

    private LazyDataTable<Review> lazyModel;

    private Page<Review> reviewPages;

    private String visiblePaginator;
    private List<Review> theBestReview;

    @PostConstruct
    public void init() {
//        if (userController.isUserLogin()) {
//            user = userController.getSelectedUser();
//        }else {
//            user = userDao.get(0L);
//        }
        selectedReview=new Review(4);
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public String vizibilityAction() {
        return reviewDao.isVisibility(reviewPages,MIN_PAGE_SIZE);
    }

    public void save() {
        selectedReview.setGood(selectedGood);
        reviewDao.save(selectedReview);

        selectedReview=new Review(user, selectedGood,4 );
    }

    @Override
    public Page<Review> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = REVIEW_SEARCH_COLUMN;
        }

        if (sortDirection == null) {
            sortDirection = REVIEW_SORT_DIRECTION;
        }
        reviewPages = reviewDao.searchGood(pageNumber, pageSize, sortField, sortDirection, selectedReview.getGood().getIdGood());

        visiblePaginator="visible" + vizibilityAction();

        return reviewPages;
    }

    @Override
    public void addAction() {
        selectedReview = new Review(4);
    }

    public void newReview(User user){
        this.user=user;
        if (selectedGood!=null) {
            selectedReview = new Review(user, selectedGood,4);
        }else {
            selectedReview = new Review(user,4);
        }
    }

    @Override
    public void editAction() {
        selectedGood=selectedReview.getGood();
    }

    @Override
    public void deleteAction() {
        reviewDao.delete(selectedReview);
    }

    public List<Review> getAll() {
        return reviewDao.getAll(new Sort(Sort.Direction.ASC, REVIEW_SEARCH_COLUMN));
    }

    public void loadData(){
//        goodController.setShowShop(false);
        selectedGood = goodDao.get(selectedGoodId);
        selectedReview.setGood(selectedGood);
        goodController.setSelectedGood(selectedGoodId);
    }

    public List<Review> getTheBestReview() {
        theBestReview=reviewDao.findByTheBestEquals();
        return theBestReview;
    }
}
