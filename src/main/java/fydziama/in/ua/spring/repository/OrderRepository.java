package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //findBy (Order.status | User.name) - IgnoreCase - OrderBy
    List<Order> findByUserNameContainingIgnoreCaseOrderByUserName(String name);

    Page<Order> findByUserNameContainingIgnoreCaseOrderByUserName(String name, Pageable pageable);
}