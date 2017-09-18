package fydziama.in.ua.dao;

import fydziama.in.ua.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends GeneralDao<OrderDetail> {
    List<OrderDetail> searchUser(long idUser);
}
