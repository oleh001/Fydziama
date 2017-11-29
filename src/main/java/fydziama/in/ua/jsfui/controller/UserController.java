package fydziama.in.ua.jsfui.controller;

import com.google.common.hash.Hashing;
import fydziama.in.ua.dao.UserDao;
import fydziama.in.ua.entity.Order;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class UserController extends AbstractController<User> {

    public static final String USER_SEARCH_COLUMN = "name";

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderDetailController orderDetailController;

    @Autowired
    private ReviewController reviewController;

    private User selectedUser = new User();

    private User selectedVirtualUser;

    private String userNameLogin;
    private String userPasswordLogin;
    private String userRememberLogin;
    private boolean userCheckedLogin;

    private boolean userRegistration;
    private boolean userVirtualCart;


    private LazyDataTable<User> lazyModel;

    private Page<User> userPages;

    private String visiblePaginator;

    private boolean userLogin = false;

    @PostConstruct
    public void init() {
        createVirtualUser();
        lazyModel = new LazyDataTable(this);
    }

    @Override
    public String vizibilityAction() {
        return userDao.isVisibility(userPages, countPages);
    }

    public void save() {
//        Save | Update User
        userDao.save(selectedUser);
    }

    @Override
    public Page<User> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = USER_SEARCH_COLUMN;
        }
        userPages = userDao.search(pageNumber, pageSize, sortField, sortDirection);

        visiblePaginator = "visible" + vizibilityAction();

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

    public void closeDialogLogin() {
        RequestContext.getCurrentInstance().execute("PF('dialogLoginUser').hide()");
    }

    public void closeDialogRregistration() {
        RequestContext.getCurrentInstance().execute("PF('dialogRegistrationUser').hide()");
    }

    public void openDialogLogin() {
        RequestContext.getCurrentInstance().execute("PF('dialogLoginUser').show()");
    }

    public void validLogin(FacesContext context, UIComponent component, Object value) {
        String newLog = (String) value;
        log.log(Level.WARNING, newLog);
        if (getAllLogin().contains(newLog)) {
            ((UIInput) component).setValid(false);
//            FacesMessage message=new FacesMessage(ResourceBundle.getBundle("fydziama").getString("that_username_is_already_in_use"));
            //            context.addMessage(component.getClientId(context),message);
//            FacesContext.getCurrentInstance().addMessage("delivery-info:nickName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "44444444444444444444444"));
            String message = ResourceBundle.getBundle("fydziama").getString("that_username_is_already_in_use");
            FacesContext.getCurrentInstance().addMessage("delivery-info:nickName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
        }
    }

    public List<String> getAllLogin() {
        List<String> allLogin = new ArrayList<>();
        allLogin.addAll(userDao.allLogin());
        if (selectedUser.getIdUser() != null) {
            allLogin.remove(selectedUser.getLogin());
        }
        return allLogin;
    }

    public void userTrueOrFalse(boolean bool) {
        userLogin = bool;
    }

    public void registration() {

//        For Registration User
        selectedUser.setPassword(generatePassword(selectedUser.getPassword()));
        save();
//            Save WishList
        if (orderController.isOrderWishListBool()) {
            orderController.getSelectedOrderWishList().setUser(selectedUser);
            orderController.getOrderDao().save(orderController.getSelectedOrderWishList());
        }
//            Save Order Start
        if (orderController.isOrderStartBool()) {
            orderController.getSelectedOrderStart().setUser(selectedUser);
            orderController.getOrderDao().save(orderController.getSelectedOrderStart());
        }
//            Save Order Confirmed
        if (orderController.isOrderConfirmedBool()) {
            for (Order order : orderController.getOrderConfirmed()) {
                order.setUser(selectedUser);
                orderController.getOrderDao().save(order);
            }
        }

        userTrueOrFalse(true);

        reviewController.newReview(selectedUser);

        sendMail(selectedUser);
    }

    public String logIn() {
        log.log(Level.WARNING, "111");
        User userLogin = userDao.checkoutLogin(userNameLogin, generatePassword(userPasswordLogin));
        log.log(Level.WARNING, String.valueOf(userLogin));
        if (String.valueOf(userLogin).equals("null")) {
            log.log(Level.WARNING, "222");
//                ((UIInput) component).setValid(false);

            String message = ResourceBundle.getBundle("fydziama").getString("wrong_username_or_password");
            FacesContext.getCurrentInstance().addMessage("login-form:user_login", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
        } else {
            log.log(Level.WARNING, "333");
            selectedUser = userLogin;
            userTrueOrFalse(true);
            saveOrDeleteInputUser(userCheckedLogin);
            RequestContext.getCurrentInstance().execute("PF('dialogLoginUser').hide()");

            orderController.searchOrderDetail();
            reviewController.newReview(selectedUser);
        }
        log.log(Level.WARNING, "444");
        return null;
    }

    public void logout() {
        selectedUser = new User();
        userTrueOrFalse(false);
//        orderController.setOrderWishListBool(false);
//        orderController.setOrderStartBool(false);
        orderController.searchOrderDetail();
        reviewController.newReview(selectedVirtualUser);
    }

    public void createVirtualUser() {
        Long maxId = userDao.maxUserId() + 1;
        if (selectedVirtualUser == null) {
            selectedVirtualUser = new User("User" + maxId, "email", "123", "user" + maxId, generatePassword("pass" + maxId));
            userDao.save(selectedVirtualUser);
        }
        log.log(Level.WARNING, selectedVirtualUser.toString());

//        orderController.searchOrderDetail();
    }

    public String generatePassword(String pass) {
        return Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
    }

    public void saveOrDeleteInputUser(boolean bool) {
        if (!bool) {
            userNameLogin = null;
            userPasswordLogin = null;
        }
    }

    public void sendMail(User user) {
//        SSLSender sslSender=new SSLSender("olehkostiuk001@gmail.com", "oleh6787qws5924460");
//        TLSSender tlsSender=new TLSSender("olehkostiuk001@gmail.com", "oleh6787qws5924460");
//        tlsSender.send("This is Subject", "TLS: This is text!", "olehkostiuk001@gmail.com", "olehkostiuk001@ukr.net");
//        sslSender.send("This is Subject", "SSL: This is text!", "olehkostiuk001@gmail.com", "olehkostiuk001@ukr.net");
    }

    public int howManyYears() {
        return 30;
    }

    public int howManyYears(Date date) {
        return 30;
    }
}
