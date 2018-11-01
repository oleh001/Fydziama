package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.RecomendationDao;
import fydziama.in.ua.entity.Recomendation;
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
public class RecomendationController extends AbstractController<Recomendation> {

    @Autowired
    private RecomendationDao recomendationDao;

    private List<Recomendation> recomendation;

    public List initialization() {
        recomendation = recomendationDao.findByVisibilityEquals();
        return recomendation;
    }

    @Override
    public boolean vizibilityAction() {
        return false;
    }

    @Override
    public Page<Recomendation> search(int first, int count, String sortField, Sort.Direction sortDirection) {
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
}
