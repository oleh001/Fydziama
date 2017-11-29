package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.OrderDetailDao;
import fydziama.in.ua.entity.Good;
import fydziama.in.ua.entity.Order;
import fydziama.in.ua.entity.OrderDetail;
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
import java.util.List;
import java.util.logging.Level;

@ManagedBean
@SessionScoped
@Component
@Getter
@Setter
@Log
public class OrderDetailController extends AbstractController<OrderDetail> {

    public static final String ORDER_DETAIL_SEARCH_COLUMN = "idOrderDetail";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderController orderController;

    @Autowired
    private UserController userController;

    private long selectedOrderStart;
    private long selectedOrderWishList;

    private boolean orderDetWishListBool = false;

    private OrderDetail selectedOrderDetail = new OrderDetail();
    private OrderDetail selectedWishList = new OrderDetail();
    private OrderDetail addOrderDetail = new OrderDetail();

    private int quantity = 1;

    private LazyDataTable<OrderDetail> lazyModel;

    private Page<OrderDetail> orderDetailPage;
    private List<OrderDetail> orderDetailList;

    private String visiblePaginator;

    private float priceDelivery = 10;
    private float priceDishes;
    private float totalPrice;
    private int totalQuantity;
    private String codeConfirmed;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void saveOrderDetail() {
        orderDetailDao.save(selectedOrderDetail);
        selectedOrderDetail = new OrderDetail();
    }

    public void saveWishList() {
        orderDetailDao.save(selectedWishList);
        selectedWishList = new OrderDetail();
    }

    public void save(OrderDetail orderDetail) {
        orderDetailDao.save(orderDetail);
    }

    @Override
    public String vizibilityAction() {
        return orderDetailDao.isVisibility(orderDetailPage, countPages);
    }

    @Override
    public Page<OrderDetail> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator = "visible" + vizibilityAction();
        return null;
    }

    @Override
    public void addAction() {
        selectedOrderDetail = new OrderDetail();
    }

    @Override
    public void editAction() {
    }

    @Override
    public void deleteAction() {
        orderDetailDao.delete(selectedOrderDetail);
    }

    public void deleteAction(OrderDetail orderDetail) {
        orderDetailDao.delete(orderDetail);
        orderController.getSelectedOrderStart().getOrderDetails().remove(orderDetail);
        updateTotalPrice();
    }

    public List<OrderDetail> getAll() {
        return orderDetailDao.getAll(new Sort(Sort.Direction.ASC, ORDER_DETAIL_SEARCH_COLUMN));
    }

    public List<OrderDetail> showOrderDetByUser(long idUser) {
        return orderDetailDao.searchUser(idUser);
    }

    public List<OrderDetail> showOrderDetByOrder() {
        log.log(Level.WARNING, "1111");
        if (orderController.getSelectedOrderStart() == null) {
            orderController.setOrderStart();
        }
        selectedOrderStart = orderController.isOrderStartBool() ? orderController.getSelectedOrderStart().getIdOrder() : 0L;
        orderDetailList = orderDetailDao.searchOrder(selectedOrderStart);
        updateTotalPrice();
        return orderDetailList;
    }

    public List<OrderDetail> showOrderDetByOrder(Order order) {
        orderDetailList = orderDetailDao.searchOrder(order.getIdOrder());
        if (!orderController.isOrderStartBool() && !orderController.isOrderConfirmedBool()) {
            codeConfirmed = order.getCodeConfirmed().substring(0, 10);
            updateTotalPrice();
            orderController.setOrderConfirmedBool(true);
        }
        return orderDetailList;
    }

//    public boolean searchWishList(){
//        if (orderDetWishListBool){
//            return orderDetWishListBool;
//        }else{
//            orderController.setOrderWishList();
//
//
//            selectedOrderWishList = orderController.isOrderStartBool() ? orderController.getSelectedOrderStart().getIdOrder() : 0L;
//            orderDetailList = orderDetailDao.searchOrder(selectedOrderStart);
//
//        }
//    }


//    public List<OrderDetail> showWishList() {
////        if (orderController.getSelectedOrderWishList() == null) {
//            orderController.setOrderWishList();
//            selectedOrderWishList = orderController.getSelectedOrderWishList().getIdOrder();
////        }
//        return orderDetailDao.searchOrder(selectedOrderWishList);
//    }

    public void updateQuantity(int quantity){
        if (quantity>0) {
            this.quantity += quantity;
        }else{
            if (this.quantity>1){
                this.quantity += quantity;
            }
        }
    }

    public void updateQuantitySubtotal(OrderDetail ordDet, int quantity) {
        log.log(Level.WARNING, String.valueOf(ordDet.getQuantity()));
        ordDet.setQuantity(ordDet.getQuantity()+quantity);
        orderDetailDao.save(ordDet);
        log.log(Level.WARNING, String.valueOf(ordDet.getQuantity()));
        updateTotalPrice();
    }

    public void addToCart(Good good) {
//        boolean saveGood = false;
//        for (OrderDetail oldOrderDetail : showOrderDetByOrder()) {
//            if (oldOrderDetail.getGood().getIdGood().equals(good.getIdGood())) {
//                oldOrderDetail.setQuantity(oldOrderDetail.getQuantity() + quantity);
//                selectedOrderDetail = oldOrderDetail;
//                saveOrderDetail();
//                saveGood = true;
//                break;
//            }
//        }
//        if (saveGood == false) {
//            selectedOrderDetail = new OrderDetail(orderController.getSelectedOrderStart(), good, quantity);
//            saveOrderDetail();
//        }
//        quantity = 1;
    }

    public void addToWishList(Good good) {

//        boolean saveGood = false;
//        if (orderWishListEnabled) {
//            for (OrderDetail oldWishList : showWishList()) {
//                if (oldWishList.getGood().getIdGood().equals(good.getIdGood())) {
//                    saveGood = true;
//                    break;
//                }
//            }
//            if (saveGood == false) {
//                selectedWishList = new OrderDetail(orderController.getSelectedOrderWishList(), good, quantity);
//                saveWishList();
//            }
//        } else {
//            orderWishListEnabled = true;
//            Order order = null;
//            if (userController.isUserLogin()) {
//                orderController.setSelectedOrderWishList(new Order(userController.getSelectedUser(), OrderStatus.WISHLIST));
//            } else {
//                orderController.setSelectedOrderWishList(new Order(userController.getSelectedVirtualUser(), OrderStatus.WISHLIST));
//
//            }
//            orderController.getOrderDao().save(orderController.getSelectedOrderWishList());
//
//            selectedWishList = new OrderDetail(orderController.getSelectedOrderWishList(), good, quantity);
//            saveWishList();
//        }
    }

    public void updateTotalPrice() {
        totalQuantity = 0;
        priceDishes = 0;
        totalPrice = 0;
        if (orderDetailList != null) {
            for (OrderDetail orderDetail : orderDetailList) {
                totalQuantity += orderDetail.getQuantity();
                priceDishes += orderDetail.getQuantity() * orderDetail.getGood().getPrice();
            }
            totalPrice = priceDishes + priceDelivery;
        }
    }

}
