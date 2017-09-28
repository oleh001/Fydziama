package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.FaqDao;
import fydziama.in.ua.entity.Faq;
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
public class FaqController extends AbstractController<Faq>  {

    public static final String FAQ_SEARCH_COLUMN = "position";

    @Autowired
    private FaqDao faqDao;

    private Faq selectedFaq=new Faq();

    private LazyDataTable<Faq> lazyModel;

    private Page<Faq> faqPages;
    private List<Faq> faqList;
    private List<Faq> faqSteps;
    private List<Faq> faqQuestions;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public String vizibilityAction() {
        return faqDao.isVisibility(faqPages,countPages);
    }

    public void save() {
        faqDao.save(selectedFaq);
        selectedFaq = new Faq();
    }

    @Override
    public Page<Faq> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public void addAction() {
selectedFaq=new Faq();
    }

    @Override
    public void editAction() {

    }

    @Override
    public void deleteAction() {
        faqDao.delete(selectedFaq);
    }

    public List<Faq> getFaqSteps() {
        faqSteps = faqDao.findBySteps();
        return faqSteps;
    }

    public List<Faq> getFaqQuestions() {
        faqQuestions = faqDao.findByQuestions();
        return faqSteps;
    }
}
