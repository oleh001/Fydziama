package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.BannerDao;
import fydziama.in.ua.entity.Banner;
import fydziama.in.ua.entity.BannerType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class BannerController extends AbstractController<Banner> {

    public static final String BANNER_SEARCH_COLUMN = "type";

    @Autowired
    private BannerDao bannerDao;

    private Banner selectedBanner;

    @Override
    public boolean vizibilityAction() {
        return false;
    }

    @Override
    public Page<Banner> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    public void save() {
        bannerDao.save(selectedBanner);
    }

    @Override
    public void addAction() {
        selectedBanner = new Banner();
    }

    @Override
    public void editAction() {

    }

    @Override
    public void deleteAction() {
        bannerDao.delete(selectedBanner);
    }

    public List<Banner> getAll() {
        return bannerDao.getAll(new Sort(Sort.Direction.ASC, BANNER_SEARCH_COLUMN));
    }

    public List<Banner> getBannerType(String type) {
        switch (type) {
            case "BIG":
                return bannerDao.getBannerWithType(BannerType.BIG);
            case "SMALL":
                return bannerDao.getBannerWithType(BannerType.SMALL);
            case "DELIVERY":
                return bannerDao.getBannerWithType(BannerType.DELIVERY);
            default:
                return null;
        }
    }
}
