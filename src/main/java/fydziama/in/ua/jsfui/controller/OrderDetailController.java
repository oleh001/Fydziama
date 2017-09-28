package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.OrderDetailDao;
import fydziama.in.ua.entity.Good;
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

    private long selectedOrderStart;
    private long selectedOrderWishList;

    private OrderDetail selectedOrderDetail = new OrderDetail();
    private OrderDetail selectedWishList = new OrderDetail();
    private OrderDetail addOrderDetail = new OrderDetail();

    private int quantity = 1;

    private LazyDataTable<OrderDetail> lazyModel;

    private Page<OrderDetail> orderDetailPage;
    private List<OrderDetail> orderDetailList;

    private String visiblePaginator;

    private float priceDelivery=10;
    private float priceDishes;
    private float totalPrice;
    private int totalQuantity;

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
//        log.log(Level.WARNING, String.valueOf(selectedOrderDetail.getIdOrderDetail()));
        orderDetailDao.delete(selectedOrderDetail);
    }

    public List<OrderDetail> getAll() {
        return orderDetailDao.getAll(new Sort(Sort.Direction.ASC, ORDER_DETAIL_SEARCH_COLUMN));
    }

    public List<OrderDetail> showOrderDetByUser(long idUser) {
        return orderDetailDao.searchUser(idUser);
    }

    public List<OrderDetail> showOrderDetByOrder() {
        if (orderController.getSelectedOrderStart() == null) {
            orderController.setOrderStart();
            selectedOrderStart = orderController.getSelectedOrderStart().getIdOrder();
        }
        orderDetailList=orderDetailDao.searchOrder(selectedOrderStart);
        return orderDetailList;
    }

    public List<OrderDetail> showWishList() {
        if (orderController.getSelectedOrderWishList() == null) {
            orderController.setOrderWishList();
            selectedOrderWishList = orderController.getSelectedOrderWishList().getIdOrder();
        }
        return orderDetailDao.searchOrder(selectedOrderWishList);
    }

    public void updateQuantitySubtotal(OrderDetail ordDet) {
        orderDetailDao.save(ordDet);
        updateTotalPrice();
    }

    public void addToCart(Good good) {
        boolean saveGood = false;
        for (OrderDetail oldOrderDetail : showOrderDetByOrder()) {
            if (oldOrderDetail.getGood().getIdGood().equals(good.getIdGood())) {
                oldOrderDetail.setQuantity(oldOrderDetail.getQuantity() + quantity);
                selectedOrderDetail = oldOrderDetail;
                saveOrderDetail();
                saveGood = true;
                break;
            }
        }
        if (saveGood == false) {
            selectedOrderDetail = new OrderDetail(orderController.getSelectedOrderStart(), good, quantity);
            saveOrderDetail();
        }
        quantity=1;
    }

    public void addToWishList(Good good) {
        boolean saveGood = false;
        for (OrderDetail oldWishList : showWishList()) {
            if (oldWishList.getGood().getIdGood().equals(good.getIdGood())) {
                saveGood = true;
                break;
            }
        }
        if (saveGood == false) {
            selectedWishList = new OrderDetail(orderController.getSelectedOrderWishList(), good, quantity);
            saveWishList();
        }
    }

    public void updateTotalPrice(){
        totalQuantity=0;
        priceDishes=0;
        totalPrice=0;
        for (OrderDetail orderDetail: orderDetailList){
            totalQuantity+=orderDetail.getQuantity();
            priceDishes+=orderDetail.getQuantity()*orderDetail.getGood().getPrice();
        }
        totalPrice=priceDishes+priceDelivery;

    }

}
