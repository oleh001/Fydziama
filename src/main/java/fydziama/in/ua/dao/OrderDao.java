package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Order;
import fydziama.in.ua.entity.OrderStatus;

public interface OrderDao extends GeneralDao<Order> {
    Order[] getOrderWithStatus(long idUser, OrderStatus status);
}
