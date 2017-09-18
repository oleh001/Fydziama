package fydziama.in.ua.jsfui.controller;

import fydziama.in.ua.dao.OrderDao;
import fydziama.in.ua.entity.Order;
import fydziama.in.ua.jsfui.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
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
public class OrderController extends AbstractController<Order> {

    public static final String ORDER_SEARCH_COLUMN = "idOrder";

    @Autowired
    private OrderDao orderDao;

    private Order selectedOrder=new Order();

    private LazyDataTable<Order> lazyModel;

    private Page<Order> orderPage;

    private String visiblePaginator;

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
        return orderDao.isVisibility(orderPage,countPages);
    }

    @Override
    public Page<Order> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        visiblePaginator="visible" + vizibilityAction();
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
}

