package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.AllDataDao;
import fydziama.in.ua.entity.AllData;
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
import java.util.logging.Level;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class AllDataController extends AbstractController<AllData> {

    @Autowired
    private AllDataDao allDataDao;

    @Autowired
    private ReviewController reviewController;

    @Autowired
    private GoodController goodController;

    private AllData allData;

    private String facebook;
    private String twitter;
    private String googlePlus;
    private String instagram;

    private IncludeFile includeFileEnumSelected;

    @PostConstruct
    public void init() {
        allData = allDataDao.get(1L);
        inputSocialNetwork();
        includeFileEnumSelected = IncludeFile.INDEX;
    }


    @Override
    public boolean vizibilityAction() {
        return false;
    }

    @Override
    public Page<AllData> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public void addAction() {

    }

    @Override
    public void editAction() {

    }

    @Override
    public void deleteAction() {

    }

    public String returnUrl() {
        log.log(Level.WARNING, "?dish=" + reviewController.getSelectedGoodId() + "&amp;cat=" + goodController.getSelectedBrandId() + "&amp;page=" + goodController.pageNumber + 1);
//        switch (includeFileEnumSelected.name()) {
//            case "INDEX":
//                return "";
//            case "SHOPR":
//                return "gfghfg";
//            case "SHOPL":
//                return "";
//            case "FAQ":
//                return "";
//            case "PROFILE":
//                return "";
//            case "CHECKOUT":
//                return "";
//        }
        return "?dish=" + reviewController.getSelectedGoodId() + "&cat=" + goodController.getSelectedBrandId() + "&page=" + (goodController.pageNumber + 1);
    }
    public String returnUUU() {
        log.log(Level.WARNING, "?dish=" + reviewController.getSelectedGoodId() + "&amp;cat=" + goodController.getSelectedBrandId() + "&amp;page=" + goodController.pageNumber + 1);
//        switch (includeFileEnumSelected.name()) {
//            case "INDEX":
//                return "?";
//            case "SHOPR":
//                return "gfghfg";
//            case "SHOPL":
//                return "";
//            case "FAQ":
//                return "";
//            case "PROFILE":
//                return "";
//            case "CHECKOUT":
//                return "";
//        }
        return "{cat:" + goodController.getSelectedBrandId() + "} + {dish:" +  reviewController.getSelectedGoodId() + "} + {page:" + goodController.pageNumber+1;
    }


//    {cat:"#{goodController.selectedBrandId}"} + {dish:"#{reviewController.selectedGoodId}"} + {page:"#{controller.pageNumber+1}"

    public void changePage(String page) {
        log.log(Level.WARNING, page.toUpperCase());
        includeFileEnumSelected = IncludeFile.valueOf(page.toUpperCase());
    }

    public void inputSocialNetwork() {
        String[] social = allData.getUrlOnSocialNetwork().split("\\|");
        for (String soc : social) {
            if (soc.split("facebook").length == 2) {
                facebook = soc;
            } else if (soc.split("twitter").length == 2) {
                twitter = soc;
            } else if (soc.split("twitter").length == 2) {
                googlePlus = soc;
            } else if (soc.split("instagram").length == 2) {
                instagram = soc;
            }
        }
    }
}
