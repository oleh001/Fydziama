package fydziama.in.ua.jsfui.controller;

import com.google.common.hash.Hashing;
import fydziama.in.ua.dao.OrderDao;
import fydziama.in.ua.entity.Order;
import fydziama.in.ua.entity.OrderStatus;
import fydziama.in.ua.entity.OrderZone;
import fydziama.in.ua.jsfui.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class OrderController extends AbstractController<Order> {

    public static final String ORDER_SEARCH_COLUMN = "idOrder";

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserController userController;

    private Order selectedOrder = new Order();

    private Order selectedOrderStart;
    private Order selectedOrderWishList;

    private boolean orderStartBool=false;
    private boolean orderConfirmedBool=false;

    private Order[] orderConfirmed;
    private Order[] orderFinish;

    private LazyDataTable<Order> lazyModel;

    private Page<Order> orderPage;

    private String visiblePaginator;

//    private OrderZone orderZone =OrderZone.ZONE1;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void save() {
        orderDao.save(selectedOrder);
        selectedOrder = new Order();
    }

    @Override
    public String vizibilityAction() {
        return orderDao.isVisibility(orderPage, countPages);
    }

    @Override
    public Page<Order> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator = "visible" + vizibilityAction();
        return null;
    }

    @Override
    public void addAction() {
        selectedOrder = new Order();
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        orderDao.delete(selectedOrder);
    }

    public List<Order> getAll() {
        return orderDao.getAll(new Sort(Sort.Direction.ASC, ORDER_SEARCH_COLUMN));
    }

    public void setOrderStart() {
        log.log(Level.WARNING, "2222");
        Order[] order=orderDao.getOrderWithStatus(userController.getUserDao().get(1L).getIdUser(), OrderStatus.START);
        selectedOrderStart = order.length>0?order[0]:null;
        orderStartBool=order.length>0;
        orderConfirmedBool=false;
    }

    public boolean showTabStart() {
        setOrderStart();
        return orderStartBool;
    }


    public void setOrderConfirmed() {
        orderConfirmed = orderDao.getOrderWithStatus(userController.getUserDao().get(1L).getIdUser(), OrderStatus.CONFIRMED);
    }

    public void setOrderFinish() {
        orderFinish = orderDao.getOrderWithStatus(userController.getUserDao().get(1L).getIdUser(), OrderStatus.FINISH);

    }

    public void setOrderWishList() {
        selectedOrderWishList = orderDao.getOrderWithStatus(userController.getUserDao().get(1L).getIdUser(), OrderStatus.WISHLIST)[0];
    }

    public void updateZone(OrderZone orderZone){
        log.log(Level.WARNING, selectedOrderStart.getZone().toString());
//        selectedOrderStart.setZone(orderZone);
//        log.log(Level.WARNING, selectedOrderStart.getZone().toString());
    }

    public Order showOrderStart(){
        setOrderStart();
        return getSelectedOrderStart();
    }

    public Order[] showOrderConfirmed(){
        setOrderConfirmed();
        return getOrderConfirmed();
    }

    public Order[] showOrderFinish(){

        setOrderFinish();
        return getOrderFinish();
    }

    public void addOrderConfirmed(){
        selectedOrderStart.setStatus(OrderStatus.CONFIRMED);
        selectedOrderStart.setOrderDate(new Date());
        selectedOrderStart.setCodeConfirmed(generateCodeConfirmed());
        selectedOrder=selectedOrderStart;
        selectedOrderStart=null;
        orderStartBool=false;
//        setOrderConfirmedBool(true);
        save();
    }

    public String generateCodeConfirmed(){
        String code="";
        code+=selectedOrderStart.getIdOrder();
        code+=selectedOrderStart.getUser().getName();
        code+=selectedOrderStart.getOrderDate();
        return Hashing.sha256()
                .hashString(code, StandardCharsets.UTF_8)
                .toString();
    }
}

