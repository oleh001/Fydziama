package fydziama.in.ua.jsfui.converter;


import com.google.common.base.Strings;
import fydziama.in.ua.dao.ZoneDao;
import fydziama.in.ua.entity.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Zone.class)
@Component
public class ZoneConverter implements Converter {

    @Autowired
    private ZoneDao zoneDao;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }
        return zoneDao.get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return null;
        }
        return ((Zone) value).getIdZone().toString();
    }
}
