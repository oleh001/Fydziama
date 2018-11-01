package fydziama.in.ua.dao.impl;

import fydziama.in.ua.dao.OrderDetailDao;
import fydziama.in.ua.entity.OrderDetail;
import fydziama.in.ua.spring.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailService implements OrderDetailDao {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public boolean isVisibility(List<OrderDetail> obj, int count) {
        return obj.size()>count;
    }

    @Override
    public boolean isVisibility(Page<OrderDetail> obj, int count) {
        return obj.getTotalPages()>count;
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetail> getAll(Sort sort) {
        return orderDetailRepository.findAll();
    }

    @Override
    public Page<OrderDetail> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return orderDetailRepository.findAll(new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    @Override
    public List<OrderDetail> search(String... searchString) {
        return orderDetailRepository.findByGoodNameContainingIgnoreCaseOrderByGoodName(searchString[0]);
    }

    @Override
    public Page<OrderDetail> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return orderDetailRepository.findByGoodNameContainingIgnoreCaseOrderByGoodName(searchString[0], new PageRequest(pageNumber, pageSize, new Sort(sortDirection, sortField)));
    }

    public List<OrderDetail> searchUser(long idUser) {
        return orderDetailRepository.findByOrderUserIdUserOrderByGoodName(idUser);
    }

    public List<OrderDetail> searchOrder(long idOrder) {
        return orderDetailRepository.findByOrderIdOrderOrderByGoodName(idOrder);
    }

    @Override
    public OrderDetail get(long id) {
        return orderDetailRepository.findOne(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }
}

