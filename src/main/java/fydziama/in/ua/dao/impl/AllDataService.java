package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.AllDataDao;
import fydziama.in.ua.entity.AllData;
import fydziama.in.ua.spring.repository.AllDataRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log
public class AllDataService implements AllDataDao {

    @Autowired
    private AllDataRepository allDataRepository;

    @Override
    public boolean isVisibility(List<AllData> obj, int count) {
        return false;
    }

    @Override
    public boolean isVisibility(Page<AllData> obj, int count) {
        return false;
    }

    @Override
    public List<AllData> getAll() {
        return allDataRepository.findAll();
    }

    @Override
    public List<AllData> search(String... searchString) {
        return null;
    }

    @Override
    public AllData get(long id) {
        return allDataRepository.findOne(id);
    }

    @Override
    public AllData save(AllData allData) {
        return allDataRepository.save(allData);
    }

    @Override
    public void delete(AllData allData) {
        allDataRepository.delete(allData);
    }

    @Override
    public List<AllData> getAll(Sort sort) {
        return allDataRepository.findAll();
    }

    @Override
    public Page<AllData> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return allDataRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<AllData> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
