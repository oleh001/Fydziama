package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.FotoOfferDao;
import fydziama.in.ua.entity.FotoOffer;
import fydziama.in.ua.spring.repository.FotoOfferRepository;
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
public class FotoOfferService implements FotoOfferDao {

    @Autowired
    private FotoOfferRepository fotoOfferRepository;

    @Override
    public boolean isVisibility(List<FotoOffer> obj, int count) {
        return false;
    }

    @Override
    public boolean isVisibility(Page<FotoOffer> obj, int count) {
        return false;
    }

    @Override
    public List<FotoOffer> getAll() {
        return fotoOfferRepository.findAll();
    }

    @Override
    public List<FotoOffer> search(String... searchString) {
        return null;
    }

    @Override
    public FotoOffer get(long id) {
        return fotoOfferRepository.findOne(id);
    }

    @Override
    public FotoOffer save(FotoOffer fotoOffer) {
        return fotoOfferRepository.save(fotoOffer);
    }

    @Override
    public void delete(FotoOffer fotoOffer) {
        fotoOfferRepository.delete(fotoOffer);
    }

    @Override
    public List<FotoOffer> getAll(Sort sort) {
        return fotoOfferRepository.findAll();
    }

    @Override
    public Page<FotoOffer> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return fotoOfferRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<FotoOffer> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
