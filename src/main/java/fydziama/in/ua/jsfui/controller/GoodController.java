package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.GoodDao;
import fydziama.in.ua.entity.Good;
import fydziama.in.ua.entity.ShopType;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.logging.Level;

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

    //    For Create Search Good
//    private int pageNumber = 0;
//    private int pageSizeMin = 2;
//    private int pageSize = 2;
    private String pageSearchColumn = GOOD_SEARCH_COLUMN;
//    private Sort.Direction pageSortDirection = Sort.Direction.ASC;
//    private int pageTotalPages;
//    private boolean pagePagginationVisible;
//    private Page<Good> paginationPages;
//    private List<Good> paginationList;

    private String url = "r";


    private int countStyleDish = 0;
    private String styleFadeIn = "fadeInRight";

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private ReviewController reviewController;

    private Good selectedGood = new Good();

    private LazyDataTable<Good> lazyModel;

    private Page<Good> goodPages;
    private List<Good> goodList;
    private List<Good> newDish;
    private List<Good> specialDish;

    //Latest search
    private SearchType searchType;
    private String searchText;

    @ManagedProperty(value = "#{param.dish}")
    private long selectedBrandId;

    private int catAll = 1;
    @Enumerated(EnumType.STRING)
    private ShopType selectedShopType = ShopType.grid;

    public boolean showShop = true;

    public boolean shopTrueOrDetailFalse = true;

    public String visiblePaginator;
    public String styleCircle = "red";

    @Override
    public boolean vizibilityAction() {
        return goodDao.isVisibility(paginationPages, countPages);
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);

        defaultSearch();
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
            paginationPages = goodDao.getAll(pageNumber, pageSize, sortField, sortDirection);
        } else {

            switch (searchType) {
                case SEARCH_BRAND:
                    paginationPages = goodDao.findByBrand(pageNumber, pageSize, sortField, sortDirection, selectedBrandId);
                    break;
                case SEARCH_TEXT:
                    paginationPages = goodDao.search(pageNumber, pageSize, sortField, sortDirection, searchText);
//                    goodPages.getContent().get(0).getHits().getClass()
                    break;
                case ALL:
                    paginationPages = goodDao.getAll(pageNumber, pageSize, sortField, sortDirection);
                    break;
            }
        }
//        showShop=true;
//        visiblePaginator="visible" + vizibilityAction();
        pagePagginationVisible = vizibilityAction();

        return paginationPages;
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

    public void showGoodsByBrandMenu(long brandId) {
        selectedBrandId=brandId;
        log.log(Level.WARNING,"     4353454     ");
        reviewController.setSelectedGoodId(0L);
        showGoodsByBrand(brandId);
    }

    public void showGoodsByBrand(long brandId) {
        shopTrueOrDetailFalse = true;
//        reviewController.setSelectedGoodId(0L);

        searchType = SearchType.SEARCH_BRAND;
        this.selectedBrandId = brandId;
        pageNumber = 0;
        defaultSearch();
    }

    public List<Good> getNewDish() {
        newDish = goodDao.findByNewsEquals();
        return newDish;
    }

    public List<Good> getSpecialDish() {
        specialDish = goodDao.findBySpecialsEquals();
        return specialDish;
    }

    public String getStyleFadeIn() {
        countStyleDish++;
        if (countStyleDish == 5 && styleFadeIn.equals("fadeInRight")) {
            styleFadeIn = "fadeInLeft";
            countStyleDish = 1;
        } else if (countStyleDish == 5) {
            styleFadeIn = "fadeInRight";
            countStyleDish = 1;
        }
        return styleFadeIn;
    }

    public void showAllMenu() {
        selectedBrandId=0;
        log.log(Level.WARNING,"     98767686     ");
        reviewController.setSelectedGoodId(0L);
        showAll();
    }

    public void showAll() {
        shopTrueOrDetailFalse = true;


        searchType = SearchType.ALL;
        selectedBrandId = 0;
        pageNumber = 0;
        defaultSearch();
    }

    public void searchText() {
        searchType = SearchType.SEARCH_TEXT;
    }

    public void setSelectedGood(long idGood) {
        selectedGood = goodDao.get(idGood);
    }

    public String styleAction(long idBrand) {
        return selectedBrandId == idBrand ? "brand-active" : "";
    }

    public void attributeListener() {
        log.log(Level.WARNING, String.valueOf(shopTrueOrDetailFalse) + "    111    dfg4hf");
        if (catAll == 0) {
            showAll();
            catAll = 1;
        } else if (selectedBrandId != 0) {
            searchType = SearchType.SEARCH_BRAND;
            pageNumber = 0;
            defaultSearch();
        }
        log.log(Level.WARNING, String.valueOf(shopTrueOrDetailFalse) + "    222    dfg5hf");
    }

    public void attributeListener2() {
        log.log(Level.WARNING, String.valueOf(shopTrueOrDetailFalse) + "    111    dfg4hf");

    }

    public void attributeListener3() {
    }

    public void backOrNextStateHistoryConvertUrl() {
//        Long goodId= Long.valueOf(url.split("dish=")[1].substring(0,1));
//        Long brandId= Long.valueOf(url.split("cat=")[1].substring(0,1));
//        reviewController.setSelectedGoodId(goodId);
//        setSelectedBrandId(brandId);
//        log.log(Level.WARNING, String.valueOf(reviewController.getSelectedGoodId()) + "   " + selectedBrandId);
//        attributeListener();
//        reviewController.attributeListener();
//        log.log(Level.WARNING, String.valueOf(reviewController.getSelectedGoodId()) + "   " + selectedBrandId);
        log.log(Level.WARNING, url);

//        attributeListener();
    }


    public void updateShopType(String shopType) {
        switch (shopType) {
            case "grid":
                this.selectedShopType = ShopType.grid;
                break;
            case "detail":
                this.selectedShopType = ShopType.detail;
                break;
            case "list":
                this.selectedShopType = ShopType.list;
                break;
        }
    }

    public String getStyleShopType(String shopType) {
        return shopType.equals(selectedShopType.toString()) ? "selected" : "";
    }

    public String getStyleCircle() {
        return selectedGood.getHits().toString().equals("TRUE") ? "gold" : "red";
    }

    public String getStyleCircle(Good good) {
        return good.getHits().toString().equals("TRUE") ? "gold" : "red";
    }

    public String getStyleImage() {
        return selectedGood.getHits().toString().equals("TRUE") ? "heart" : "star";
    }

    public String getStyleImage(Good good) {
        return good.getHits().toString().equals("TRUE") ? "heart" : "star";
    }

    public String toShoprUrlReturn(int category) {
        return "shopr?category=" + category + "&amp;faces-redirect=true";
    }

    public boolean shopOrDetail() {
        return true;
    }
}
