package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.RecomendationDao;
import fydziama.in.ua.entity.Recomendation;
import fydziama.in.ua.entity.Visible;
import fydziama.in.ua.spring.repository.RecomendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecomendationService  implements RecomendationDao {

    @Autowired
    private RecomendationRepository recomendationRepository;

    @Override
    public boolean isVisibility(List<Recomendation> obj, int count) {
        return false;
    }

    @Override
    public boolean isVisibility(Page<Recomendation> obj, int count) {
        return false;
    }

    @Override
    public List<Recomendation> getAll() {
        return recomendationRepository.findAll();
    }

    @Override
    public List<Recomendation> search(String... searchString) {
        return null;
    }

    @Override
    public Recomendation get(long id) {
        return recomendationRepository.findOne(id);
    }

    @Override
    public Recomendation save(Recomendation recomendation) {
        return recomendationRepository.save(recomendation);
    }

    @Override
    public void delete(Recomendation recomendation) {
        recomendationRepository.delete(recomendation);
    }

    @Override
    public List<Recomendation> getAll(Sort sort) {
        return recomendationRepository.findAll();
    }

    @Override
    public Page<Recomendation> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return recomendationRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<Recomendation> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }

    @Override
    public List<Recomendation> findByVisibilityEquals() {
        return recomendationRepository.findByVisibilityEquals(Visible.TRUE);
    }

}
