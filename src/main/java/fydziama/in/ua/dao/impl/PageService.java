package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.PageDao;
import fydziama.in.ua.entity.Pages;
import fydziama.in.ua.spring.repository.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PageService implements PageDao{

    @Autowired
    private PagesRepository pagesRepository;

    @Override
    public boolean isVisibility(List<Pages> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Pages> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Pages> getAll() {
        return pagesRepository.findAll();
    }

    @Override
    public List<Pages> getAll(Sort sort) {
        return pagesRepository.findAll();
    }

    @Override
    public Page<Pages> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return pagesRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Pages> search(String... searchString) {
        return pagesRepository.findByTitleContainingIgnoreCaseOrderByTitle(searchString[0]);
    }

    @Override
    public Page<Pages> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return pagesRepository.findByTitleContainingIgnoreCaseOrderByTitle(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Pages get(long id) {
        return pagesRepository.findOne(id);
    }

    @Override
    public Pages save(Pages pages) {
        return pagesRepository.save(pages);
    }

    @Override
    public void delete(Pages pages) {
        pagesRepository.delete(pages);
    }
}

