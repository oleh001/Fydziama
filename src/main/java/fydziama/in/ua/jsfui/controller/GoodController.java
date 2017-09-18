package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.GoodDao;
import fydziama.in.ua.entity.Good;
import fydziama.in.ua.jsfui.enums.SearchType;
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
public class GoodController extends AbstractController<Good> {

    public static final String GOOD_SEARCH_COLUMN = "name";


    public static final int DEFAULT_PAGE_SIZE = 6;
    private int rowsCount = DEFAULT_PAGE_SIZE;

    private int countStyleDish=0;
    private String styleFadeIn="fadeInRight";

    @Autowired
    private GoodDao goodDao;

    private Good selectedGood;

    private LazyDataTable<Good> lazyModel;

    private Page<Good> goodPages;
    private List<Good> goodList;
    private List<Good> newDish;
    private List<Good> specialDish;

    //Latest search
    private SearchType searchType;
    private String searchText;
    private long selectedBrandId;

    public String visiblePaginator;

    @Override
    public String vizibilityAction() {
        return goodDao.isVisibility(goodPages,countPages);
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void save() {
        goodDao.save(selectedGood);
    }

    @Override
    public Page<Good> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = GOOD_SEARCH_COLUMN;
        }

        if (searchType == null) {
            goodPages = goodDao.getAll(pageNumber, pageSize, sortField, sortDirection);
        } else {

            switch (searchType) {
                case SEARCH_BRAND:
                    goodPages = goodDao.findByBrand(pageNumber, pageSize, sortField, sortDirection, selectedBrandId);
                    break;
                case SEARCH_TEXT:
                    goodPages = goodDao.search(pageNumber, pageSize, sortField, sortDirection, searchText);
//                    goodPages.getContent().get(0).getHits().getClass()
                    break;
                case ALL:
                    goodPages = goodDao.getAll(pageNumber, pageSize, sortField, sortDirection);
                    break;
            }
        }

        visiblePaginator="visible" + vizibilityAction();

        return goodPages;
    }

    @Override
    public void addAction() {
        selectedGood = new Good();
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        goodDao.delete(selectedGood);
    }

    public void showGoodsByBrand(long brandId) {
        searchType = SearchType.SEARCH_BRAND;
        this.selectedBrandId = brandId;
    }

    public List<Good> getNewDish() {
        newDish = goodDao.findByNewsEquals();
        return newDish;
    }

    public List<Good> getSpecialDish() {
        specialDish = goodDao.findBySpecialsEquals();
        return specialDish;
    }

    public String getStyleFadeIn(){
        countStyleDish++;
        if (countStyleDish==5 && styleFadeIn=="fadeInRight"){
            styleFadeIn="fadeInLeft";
            countStyleDish=1;
        }else if (countStyleDish==5){
            styleFadeIn="fadeInRight";
            countStyleDish=1;
        }
        return styleFadeIn;
    }

    public void showAll() {
        searchType = SearchType.ALL;
    }

    public void searchText() {
        searchType = SearchType.SEARCH_TEXT;
    }

}
