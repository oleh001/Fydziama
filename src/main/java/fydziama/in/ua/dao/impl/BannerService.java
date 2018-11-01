package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.BannerDao;
import fydziama.in.ua.entity.Banner;
import fydziama.in.ua.entity.BannerType;
import fydziama.in.ua.entity.Visible;
import fydziama.in.ua.spring.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerService implements BannerDao {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public boolean isVisibility(List<Banner> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Banner> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Banner> getAll() {
        return bannerRepository.findAll();
    }

    @Override
    public List<Banner> search(String... searchString) {
        return null;
    }

    @Override
    public Banner get(long id) {
        return bannerRepository.findOne(id);
    }

    @Override
    public Banner save(Banner zone) {
        return bannerRepository.save(zone);
    }

    @Override
    public void delete(Banner zone) {
        bannerRepository.delete(zone);
    }

    @Override
    public List<Banner> getAll(Sort sort) {
        return bannerRepository.findAll();
    }

    @Override
    public Page<Banner> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return bannerRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<Banner> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }

    @Override
    public List<Banner> getBannerWithType(BannerType type){
        return bannerRepository.findByTypeEqualsAndVisibilityEquals(type, Visible.TRUE);
    }

}
