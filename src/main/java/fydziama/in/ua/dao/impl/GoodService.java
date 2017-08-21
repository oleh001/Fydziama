package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.GoodDao;
import fydziama.in.ua.entity.Good;
import fydziama.in.ua.spring.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodService implements GoodDao {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    @Override
    public List<Good> getAll(Sort sort) {
        return goodRepository.findAll();
    }

    @Override
    public Page<Good> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return goodRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Good> search(String... searchString) {
        return goodRepository.findByNameContainingIgnoreCaseOrBrandBrandContainingIgnoreCaseOrderByName(searchString[0], searchString[1]);
    }

    @Override
    public Page<Good> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return goodRepository.findByNameContainingIgnoreCaseOrBrandBrandContainingIgnoreCaseOrderByName(searchString[0], searchString[1], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Good get(long id) {
        return goodRepository.findOne(id);
    }

    @Override
    public Good save(Good good) {
        return goodRepository.save(good);
    }

    @Override
    public void delete(Good good) {
        goodRepository.delete(good);
    }


    @Override
    public Page<Good> findByBrand(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return goodRepository.findByBrandIdBrand(genreId, new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public void updateRating(long totalRating, long totalVoteCount, int avgRating, long id) {
        goodRepository.updateRating(totalRating, totalVoteCount, avgRating, id);
    }
}
