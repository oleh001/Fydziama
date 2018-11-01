package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.OrderDao;
import fydziama.in.ua.entity.Order;
import fydziama.in.ua.entity.OrderStatus;
import fydziama.in.ua.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean isVisibility(List<Order> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<Order> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAll(Sort sort) {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return orderRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<Order> search(String... searchString) {
        return orderRepository.findByUserNameContainingIgnoreCaseOrderByUserName(searchString[0]);
    }

    @Override
    public Page<Order> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return orderRepository.findByUserNameContainingIgnoreCaseOrderByUserName(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public Order get(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Order[] getOrderWithStatus(long idUser, OrderStatus status){
        return orderRepository.findByUserIdUserAndStatus(idUser, status);
    }
}

