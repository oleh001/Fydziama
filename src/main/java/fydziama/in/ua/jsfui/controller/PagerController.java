package fydziama.in.ua.jsfui.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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
public class PagerController<T> {

    //    For Create Search Page
    public int pageNumber = 0;
    public int pageSizeMin = 2;
    public int pageSize = 2;
    public String pageSearchColumn = "name";
    public Sort.Direction pageSortDirection = Sort.Direction.ASC;
    public int pageTotalPages;
    public boolean pagePagginationVisible;

    public Page<T> paginationPages;
    public List<T> mainListWithEntity;

    //    Search in Controller(Entity)
    public Page<T> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return paginationPages;
    }

    public String thisPageSelected(int page) {
        if (pageNumber == page && page == 0) {
            return "selected";
        } else if (paginationPages.isLast() && page == 2) {
            return "selected";
        } else if (pageNumber > 0 && !paginationPages.isLast() && page == 1) {
            return "selected";
        }
        return "";
    }

    public boolean thisPageDisabled(int page) {
        if (pageNumber == page && page == 0) {
            return true;
        } else if (paginationPages.isLast() && page == 2) {
            return true;
        } else if (pageNumber > 0 && !paginationPages.isLast() && page == 1) {
            return true;
        }
        return false;
    }

    public void stepNextPage() {
        if (pageNumber < pageTotalPages - 1) {
            pageNumber++;
        }

        log.log(Level.WARNING, String.valueOf(pageNumber));
        defaultSearch();
    }

    public void stepThreePage() {
        log.log(Level.WARNING, " sd " + pageNumber + "   dfg   " + paginationPages.getTotalPages());
        if (pageNumber == 0 && paginationPages.getTotalPages() < 3) {
            pageNumber += 1;
        } else if (pageNumber == 0 && paginationPages.getTotalPages() > 1) {
            pageNumber += 2;
        } else if (pageNumber < paginationPages.getTotalPages() - 1) {
            pageNumber++;
        }

        defaultSearch();
    }

    public void stepLastPage() {
        pageNumber = pageTotalPages - 1;
        defaultSearch();
    }

    public void stepFirstPage() {
        if (pageNumber > 0) {
            pageNumber = 0;
        }
        defaultSearch();
    }

    public void stepPreviousPage() {
        if (pageNumber > 0) {
            pageNumber--;
        }
        defaultSearch();
    }

    public void stepOnePage() {
        if (paginationPages.isLast() && pageNumber > 1) {
            pageNumber -= 2;
        } else if (pageNumber > 0) {
            pageNumber--;
        }

        defaultSearch();
    }

    public void stepNextOrPreviousPage() {
        if (pageNumber == 0) {
            pageNumber++;
        } else if (pageNumber > 0) {
            pageNumber--;
        }
        defaultSearch();
    }

    public String valueFirstOrPreviusPage() {
//        log.log(Level.WARNING, String.valueOf(pageNumber)+ " value first page");
        if (pageNumber == 0) {
            return String.valueOf((pageNumber + 1));
        } else if (paginationPages.isLast() && paginationPages.getTotalPages() < 3) {
            return String.valueOf((pageNumber));
        } else if (paginationPages.isLast()) {
            return String.valueOf((pageNumber - 1));
        } else return String.valueOf((pageNumber));
    }

    public String valueNextOrPreviusOrActivePage() {
        log.log(Level.WARNING, String.valueOf(pageNumber));
        if (pageNumber == 0) {
            return String.valueOf((pageNumber + 2));
        } else if (paginationPages.isLast()) {
            return String.valueOf((pageNumber));
        } else return String.valueOf((pageNumber + 1));
    }

    public String valuNextOrLastPage() {
//        log.log(Level.WARNING, String.valueOf(pageNumber)+ " value next page");
//        log.log(Level.WARNING, String.valueOf(pageNumber) + " value next page");
        if (pageNumber == 0 && paginationPages.getTotalPages() < 3) {
//            log.log(Level.WARNING, String.valueOf(pageNumber + 2) + " value next page");
            return String.valueOf((pageNumber + 2));
        } else if (pageNumber == 0) {
//            log.log(Level.WARNING, String.valueOf(pageNumber + 3) + " value next page");
            return String.valueOf((pageNumber + 3));
        } else if (paginationPages.isLast()) {
//            log.log(Level.WARNING, String.valueOf(pageNumber + 1) + " value next page");
            return String.valueOf((pageNumber + 1));
        } else {
//            log.log(Level.WARNING, String.valueOf(pageNumber + 2) + " value next page");
            return String.valueOf((pageNumber + 2));
        }
    }

    public void defaultSearch() {
        pageTotalPages = search(pageNumber, pageSize, pageSearchColumn, pageSortDirection).getTotalPages();
        mainListWithEntity = paginationPages.getContent();
        pagePagginationVisible = paginationPages.getTotalPages() > 1;

//        log.log(Level.WARNING, String.valueOf(paginationPages.getContent()) + " page");
    }

    public String returnParameters(String brand, String page, String dish){
        return "{cat:" + brand +"} + {page:" + page + "},Title, ?cat=" + brand + "&amp;page=" + page;
    }

}
