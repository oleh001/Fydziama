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
import java.util.logging.Level;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class ReviewController extends AbstractController<Review> {

    public static final String REVIEW_SEARCH_COLUMN = "reviewDate";
    public static final Sort.Direction REVIEW_SORT_DIRECTION = Sort.Direction.DESC;

    private static final int DEFAULT_PAGE_SIZE = 6;
    private static final int MIN_PAGE_SIZE = 6;
    private int rowsCount = DEFAULT_PAGE_SIZE;

    private boolean visibleHistoryPushState=false;

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
    private long selectedGoodId = 0L;

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
        selectedReview = new Review(4);
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public boolean vizibilityAction() {
        return reviewDao.isVisibility(paginationPages, countPages);
    }

    public void save() {
        selectedReview.setGood(selectedGood);
        reviewDao.save(selectedReview);

        selectedReview = new Review(user, selectedGood, 4);
    }

    @Override
    public Page<Review> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = REVIEW_SEARCH_COLUMN;
        }

        if (sortDirection == null) {
            sortDirection = REVIEW_SORT_DIRECTION;
        }
        paginationPages = reviewDao.searchGood(pageNumber, pageSize, sortField, sortDirection, selectedReview.getGood().getIdGood());

//        visiblePaginator="visible" + vizibilityAction();
        pagePagginationVisible = vizibilityAction();

        return paginationPages;
    }

    @Override
    public void addAction() {
        selectedReview = new Review(4);
    }

    public void newReview(User user) {
        this.user = user;
        if (selectedGood != null) {
            selectedReview = new Review(user, selectedGood, 4);
        } else {
            selectedReview = new Review(user, 4);
        }
    }

    @Override
    public void editAction() {
        selectedGood = selectedReview.getGood();
    }

    @Override
    public void deleteAction() {
        reviewDao.delete(selectedReview);
    }

    public List<Review> getAll() {
        return reviewDao.getAll(new Sort(Sort.Direction.ASC, REVIEW_SEARCH_COLUMN));
    }

    public void showAll() {
        pageNumber = 0;
        defaultSearch();
    }

    public void loadData() {
//        goodController.setShowShop(false);
        selectedGood = goodDao.get(selectedGoodId);
        selectedReview.setGood(selectedGood);
        goodController.setSelectedGood(selectedGoodId);
    }

    public void attributeListener() {
        if (selectedGoodId != 0L) {
            selectedGood = goodDao.get(selectedGoodId);
            selectedReview.setGood(selectedGood);
            goodController.setSelectedGood(selectedGoodId);

            goodController.shopTrueOrDetailFalse=false;
//        pageNumber = 0;
            log.log(Level.WARNING, "1111111111111111111111");
            defaultSearch();
        }else{
            goodController.shopTrueOrDetailFalse=true;
        }
        log.log(Level.WARNING, "222222222222222222222222");
    }

    public List<Review> getTheBestReview() {
        theBestReview = reviewDao.findByTheBestEquals();
        return theBestReview;
    }

    public void changeGoodId(long id) {
        selectedGoodId=id;
        pageNumber=0;
//        selectedGood = goodDao.get(selectedGoodId);
//        selectedReview.setGood(selectedGood);
//        goodController.setSelectedGood(selectedGoodId);
//
//        goodController.shopTrueOrDetailFalse = false;
        log.log(Level.WARNING, selectedGoodId + " " + String.valueOf(id) + "    dfg    dfghf");
        log.log(Level.WARNING, String.valueOf(goodController.shopTrueOrDetailFalse) + "    dfg    dfghf");
//        defaultSearch();

        attributeListener();  // Мабуть не треба бо на shopr сторінці він знову запускається
    }
}
