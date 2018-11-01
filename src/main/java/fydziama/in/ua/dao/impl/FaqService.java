package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.FaqDao;
import fydziama.in.ua.entity.Faq;
import fydziama.in.ua.entity.Visible;
import fydziama.in.ua.spring.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FaqService implements FaqDao {

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public boolean isVisibility(List<Faq> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Faq> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Faq> getAll() {
        return faqRepository.findAll();
    }

    @Override
    public List<Faq> search(String... searchString) {
        return null;
    }

    @Override
    public Faq get(long id) {
        return faqRepository.findOne(id);
    }

    @Override
    public Faq save(Faq faq) {
        return faqRepository.save(faq);
    }

    @Override
    public void delete(Faq faq) {
        faqRepository.delete(faq);
    }

    @Override
    public List<Faq> getAll(Sort sort) {
        return faqRepository.findAll();
    }

    @Override
    public Page<Faq> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return faqRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<Faq> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }


    @Override
    public List<Faq> findBySteps() {
        return faqRepository.findByStepOrderByPosition(Visible.TRUE);
    }

    @Override
    public List<Faq> findByQuestions() {
        return faqRepository.findByStepOrderByPosition(Visible.FALSE);
    }
}
