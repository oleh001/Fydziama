package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.BrandDao;
import fydziama.in.ua.entity.Brand;
import fydziama.in.ua.spring.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService implements BrandDao {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public String isVisibility(List<Brand> obj, int count) {
        return obj.size()>count?"true":"false";
    }

    @Override
    public String isVisibility(Page<Brand> obj, int count) {
        return obj.getTotalPages()>count?"true":"false";
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getAll(Sort sort) {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return brandRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Brand> search(String... searchString) {

        return brandRepository.findByBrandContainingIgnoreCaseOrderByBrand(searchString[0]);
    }

    @Override
    public Page<Brand> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return brandRepository.findByBrandContainingIgnoreCaseOrderByBrand(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Brand get(long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }

}

