package fydziama.in.ua.jsfui.controller;

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

    public static final String REVIEW_SEARCH_COLUMN = "name";
    public static final Sort.Direction REVIEW_SORT_DIRECTION= Sort.Direction.DESC;

    private static final int DEFAULT_PAGE_SIZE = 6;
    private int rowsCount = DEFAULT_PAGE_SIZE;

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    private User user;

    private Good selectedGood;

    private Review selectedReview;

    private LazyDataTable<Review> lazyModel;

    private Page<Review> reviewPages;

    private String visiblePaginator;

    @PostConstruct
    public void init() {
        user= userDao.get(3L);
        selectedReview=new Review(user, "name34", 4 );
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public String vizibilityAction() {
        return reviewDao.isVisibility(reviewPages,countPages);
    }

    public void save() {
        reviewDao.save(selectedReview);
        selectedReview=new Review(user, "name34", selectedGood,4 );
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
        selectedReview = new Review();
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

}
