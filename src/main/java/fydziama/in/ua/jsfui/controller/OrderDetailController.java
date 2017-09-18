package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.OrderDetailDao;
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
public class OrderDetailController  extends AbstractController<OrderDetail>{

    public static final String ORDER_DETAIL_SEARCH_COLUMN = "idOrderDetail";

    @Autowired
    private OrderDetailDao orderDetailDao;

    private OrderDetail selectedOrderDetail=new OrderDetail();

    private int quantity=1;

    private LazyDataTable<OrderDetail> lazyModel;

    private Page<OrderDetail> orderDetailPage;

    private String visiblePaginator;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataTable(this);
    }

    public void save() {
        orderDetailDao.save(selectedOrderDetail);
        selectedOrderDetail = new OrderDetail();
    }

    @Override
    public String vizibilityAction() {
        return orderDetailDao.isVisibility(orderDetailPage,countPages);
    }

    @Override
    public Page<OrderDetail> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator="visible" + vizibilityAction();
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

    public void updateQuantitySubtotal(OrderDetail ordDet){
        orderDetailDao.save(ordDet);
    }

}
