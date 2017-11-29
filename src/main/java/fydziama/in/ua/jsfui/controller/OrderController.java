package fydziama.in.ua.jsfui.controller;

import com.google.common.hash.Hashing;
import fydziama.in.ua.dao.OrderDao;
import fydziama.in.ua.entity.*;
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
import java.util.ArrayList;
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

    @Autowired
    private ZoneController zoneController;

    @Autowired
    private OrderDetailController orderDetailController;

    private Order selectedOrder = new Order();

    private Order selectedOrderStart;
    private Order selectedOrderWishList;

    private boolean orderStartBool = false;
    private boolean orderWishListBool = false;
    private boolean orderConfirmedBool = false;
    private boolean orderFinishBool = false;

    private Order[] orderConfirmed;
    private Order[] orderFinish;

    private LazyDataTable<Order> lazyModel;

    private Page<Order> orderPage;

    private String visiblePaginator;

//    private OrderZone orderZone =OrderZone.ZONE1;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
        searchOrderDetail();
    }

    public void save() {
        orderDao.save(selectedOrder);
        selectedOrder = new Order();
    }

    public void save(Order order) {
        orderDao.save(order);
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
        setUserOrderForStatus(OrderStatus.START);

//        selectedOrderStart = order.length > 0 ? order[0] : null;
//        orderStartBool = order.length > 0;
//        orderConfirmedBool = false;
    }

    public boolean showTabStart() {
        setOrderStart();
        return orderStartBool;
    }


    public void setOrderConfirmed() {
        setUserOrderForStatus(OrderStatus.CONFIRMED);
    }

    public void setOrderFinish() {
        setUserOrderForStatus(OrderStatus.FINISH);
    }

    public void searchOrderDetail() {
        setUserOrderForStatus(OrderStatus.WISHLIST);
        setUserOrderForStatus(OrderStatus.START);
        setUserOrderForStatus(OrderStatus.CONFIRMED);
        setUserOrderForStatus(OrderStatus.FINISH);
    }

//    public void setOrderWishList() {
//        Order[] order;
//        order = setUserForStatus(OrderStatus.WISHLIST);
//
//        selectedOrderWishList = order.length > 0 ? order[0] : null;
//        orderWishListBool = order.length > 0;


//        selectedOrderWishList = orderWishListBool ? selectedOrderWishList.getIdOrder() : 0L;
//        orderDetailList = orderDetailDao.searchOrder(selectedOrderStart);
//
//        orderDetailController.setSelectedWishList(selectedOrderWishList.getOrderDetails().get(0));
//    }

    public void setUserOrderForStatus(OrderStatus status) {
        Order[] order = null;
        if (userController.isUserLogin()) {
            order = orderDao.getOrderWithStatus(userController.getSelectedUser().getIdUser(), status);
        } else {
            order = orderDao.getOrderWithStatus(userController.getSelectedVirtualUser().getIdUser(), status);
        }
        switch (status) {
            case WISHLIST:
                selectedOrderWishList = order.length > 0 ? order[0] : null;
                orderWishListBool = order.length > 0;
                break;
            case START:
                selectedOrderStart = order.length > 0 ? order[0] : null;
                orderStartBool = order.length > 0;
                if (orderStartBool) {
                    orderDetailController.setOrderDetailList(selectedOrderStart.getOrderDetails());
                } else {
                    orderDetailController.setOrderDetailList(null);
                }
                orderDetailController.updateTotalPrice();
                break;
            case CONFIRMED:
                orderConfirmed = order;
                orderConfirmedBool = order.length > 0;
                if (!orderStartBool && orderConfirmedBool) {
                    for (Order order1 : orderConfirmed) {
                        orderDetailController.setOrderDetailList(order1.getOrderDetails());
                    }

                    orderDetailController.updateTotalPrice();
                }
                break;
            case FINISH:
                orderFinish = order;
                orderFinishBool = order.length > 0;
                break;
        }
    }

    public void updateZone(OrderZone orderZone) {
        log.log(Level.WARNING, selectedOrderStart.getZone().toString());
//        selectedOrderStart.setZone(orderZone);
//        log.log(Level.WARNING, selectedOrderStart.getZone().toString());
    }

    public Order showOrderStart() {
        setOrderStart();
        return getSelectedOrderStart();
    }

    public Order[] showOrderConfirmed() {
        setOrderConfirmed();
        return getOrderConfirmed();
    }

    public Order[] showOrderFinish() {
        setOrderFinish();
        return getOrderFinish();
    }

    public void addOrderConfirmed() {
        selectedOrderStart.setStatus(OrderStatus.CONFIRMED);
        selectedOrderStart.setOrderDate(new Date());
        selectedOrderStart.setCodeConfirmed(generateCodeConfirmed());
        selectedOrder = selectedOrderStart;
        selectedOrderStart = null;
        orderStartBool = false;
//        setOrderConfirmedBool(true);
        save();
    }

    public String generateCodeConfirmed() {
        String code = "";
        code += selectedOrderStart.getIdOrder();
        code += selectedOrderStart.getUser().getName();
        code += selectedOrderStart.getOrderDate();
        return Hashing.sha256()
                .hashString(code, StandardCharsets.UTF_8)
                .toString();
    }

    public void addToWishList(Good good) {
        boolean saveGood = false;
        if (orderWishListBool) {
            for (OrderDetail oldWishList : selectedOrderWishList.getOrderDetails()) {
                if (oldWishList.getGood().getIdGood().equals(good.getIdGood())) {
                    saveGood = true;
                    break;
                }
            }
            if (!saveGood) {
                addNewCartOrWithList(selectedOrderWishList, good);
            }
        } else {
            orderWishListBool = true;

            if (userController.isUserLogin()) {
                selectedOrderWishList = new Order(userController.getSelectedUser(), OrderStatus.WISHLIST);
            } else {
                selectedOrderWishList = new Order(userController.getSelectedVirtualUser(), OrderStatus.WISHLIST);
            }
            ArrayList<OrderDetail> list = new ArrayList<>();
            list.add(new OrderDetail(selectedOrderWishList, good, orderDetailController.getQuantity()));
            selectedOrderWishList.setOrderDetails(list);
            selectedOrderWishList.setZone(zoneController.getAll().get(0));
            save(selectedOrderWishList);

            orderDetailController.save(list.get(0));
        }
    }

    public void addToCart(Good good) {
        boolean saveGood = false;
        if (orderStartBool) {
            for (OrderDetail oldStart : selectedOrderStart.getOrderDetails()) {
                if (oldStart.getGood().getIdGood().equals(good.getIdGood())) {
                    oldStart.setQuantity(oldStart.getQuantity() + orderDetailController.getQuantity());
//                        selectedOrderDetail = oldStart;
                    save(selectedOrderStart);
                    saveGood = true;
                    break;
                }
            }
            if (!saveGood) {
                addNewCartOrWithList(selectedOrderStart, good);
            }
            orderDetailController.setQuantity(1);
        } else {
            orderStartBool = true;

            if (userController.isUserLogin()) {
                selectedOrderStart = new Order(userController.getSelectedUser(), OrderStatus.START);
            } else {
                selectedOrderStart = new Order(userController.getSelectedVirtualUser(), OrderStatus.START);
            }
            ArrayList<OrderDetail> list = new ArrayList<>();
            list.add(new OrderDetail(selectedOrderStart, good, orderDetailController.getQuantity()));
            selectedOrderStart.setOrderDetails(list);
            selectedOrderStart.setZone(zoneController.getAll().get(0));
            save(selectedOrderStart);

            orderDetailController.save(list.get(0));
        }

        orderDetailController.setOrderDetailList(selectedOrderStart.getOrderDetails());
        orderDetailController.updateTotalPrice();
    }

    public boolean addToCart2(Good good) {
        boolean saveGood = false;
        if (orderStartBool) {
            for (OrderDetail oldStart : selectedOrderStart.getOrderDetails()) {
                if (oldStart.getGood().getIdGood().equals(good.getIdGood())) {
                    oldStart.setQuantity(oldStart.getQuantity() + orderDetailController.getQuantity());
//                        selectedOrderDetail = oldStart;
                    save(selectedOrderStart);
                    saveGood = true;
                    break;
                }
            }
            if (!saveGood) {
                addNewCartOrWithList(selectedOrderStart, good);
            }
            orderDetailController.setQuantity(1);
        } else {
            orderStartBool = true;

            if (userController.isUserLogin()) {
                selectedOrderStart = new Order(userController.getSelectedUser(), OrderStatus.START);
            } else {
                selectedOrderStart = new Order(userController.getSelectedVirtualUser(), OrderStatus.START);
            }
            ArrayList<OrderDetail> list = new ArrayList<>();
            list.add(new OrderDetail(selectedOrderStart, good, orderDetailController.getQuantity()));
            selectedOrderStart.setOrderDetails(list);
            selectedOrderStart.setZone(zoneController.getAll().get(0));
            save(selectedOrderStart);

            orderDetailController.save(list.get(0));
        }

        orderDetailController.setOrderDetailList(selectedOrderStart.getOrderDetails());
        orderDetailController.updateTotalPrice();
        return false;
    }

    public void addNewCartOrWithList(Order order, Good good) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        list.add(new OrderDetail(order, good, orderDetailController.getQuantity()));
        order.getOrderDetails().add(list.get(0));
        save(order);

        orderDetailController.save(list.get(0));
    }

}

