package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.UserDao;
import fydziama.in.ua.entity.User;
import fydziama.in.ua.jsfui.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class UserController  extends AbstractController<User>  {

    public static final String USER_SEARCH_COLUMN = "name";

    @Autowired
    private UserDao userDao;

    private User selectedUser =new User();

    private LazyDataTable<User> lazyModel;

    private Page<User> userPages;

    private String visiblePaginator;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public String vizibilityAction() {
        return userDao.isVisibility(userPages,countPages);
    }

    public void save() {
        selectedUser.setLogin("qwe1566");
        selectedUser.setPassword("qwe1566");
        userDao.save(selectedUser);
    }

    @Override
    public Page<User> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = USER_SEARCH_COLUMN;
        }
        userPages = userDao.search(pageNumber, pageSize, sortField, sortDirection);

        visiblePaginator="visible" + vizibilityAction();

        return userPages;
    }

    @Override
    public void addAction() {
        selectedUser = new User();

        RequestContext.getCurrentInstance().execute("PF('dialogRegistrationUser').show()");
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        userDao.delete(selectedUser);
    }

    public List<User> getAll() {
        return userDao.getAll(new Sort(Sort.Direction.ASC, USER_SEARCH_COLUMN));
    }

    public void registration(){

    }

    public void loginAction(){

    }
}
