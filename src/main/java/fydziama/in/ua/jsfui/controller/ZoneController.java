package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.ZoneDao;
import fydziama.in.ua.entity.Zone;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class ZoneController extends AbstractController<Zone> {

    public static final String ZONE_SEARCH_COLUMN = "name";

    @Autowired
    private ZoneDao zoneDao;

    private Zone selectedZone;

    @Override
    public String vizibilityAction() {
        return null;
    }

    @Override
    public Page<Zone> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return null;
    }

    public void save() {
        zoneDao.save(selectedZone);
    }

    @Override
    public void addAction() {
        selectedZone = new Zone();
    }

    @Override
    public void editAction() {

    }

    @Override
    public void deleteAction() {
        zoneDao.delete(selectedZone);
    }
    public List<Zone> getAll() {
        return zoneDao.getAll(new Sort(Sort.Direction.ASC, ZONE_SEARCH_COLUMN));
    }

}
