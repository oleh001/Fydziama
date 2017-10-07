package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.BrandDao;
import fydziama.in.ua.entity.Brand;
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
public class BrandController extends AbstractController<Brand>{

    public static final String BRAND_SEARCH_COLUMN = "brand";

    @Autowired
    private BrandDao brandDao;

    private Brand selectedBrand=new Brand();

    private LazyDataTable<Brand> lazyModel;

    private Page<Brand> brandPages;

    private String visiblePaginator;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void save() {
        brandDao.save(selectedBrand);
        selectedBrand = new Brand();
    }

    @Override
    public String vizibilityAction() {
        return brandDao.isVisibility(brandPages,countPages);
    }

    @Override
    public Page<Brand> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator="visible" + vizibilityAction();
        return null;
    }

    @Override
    public void addAction() {
        selectedBrand = new Brand();
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        brandDao.delete(selectedBrand);
    }

    public List<Brand> getAll() {
        return brandDao.getAll(new Sort(Sort.Direction.ASC, BRAND_SEARCH_COLUMN));
    }
}
