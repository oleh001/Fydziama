package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.ZoneDao;
import fydziama.in.ua.entity.Zone;
import fydziama.in.ua.spring.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneService implements ZoneDao {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public boolean isVisibility(List<Zone> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Zone> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    public List<Zone> search(String... searchString) {
        return null;
    }

    @Override
    public Zone get(long id) {
        return zoneRepository.findOne(id);
    }

    @Override
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public void delete(Zone zone) {
        zoneRepository.delete(zone);
    }

    @Override
    public List<Zone> getAll(Sort sort) {
        return zoneRepository.findAll();
    }

    @Override
    public Page<Zone> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return zoneRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Page<Zone> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return null;
    }
}
