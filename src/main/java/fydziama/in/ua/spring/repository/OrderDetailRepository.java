package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    //findBy (Good.name) - IgnoreCase - OrderBy
    List<OrderDetail> findByGoodNameContainingIgnoreCaseOrderByGoodName(String name);

    Page<OrderDetail> findByGoodNameContainingIgnoreCaseOrderByGoodName(String name, Pageable pageable);

    List<OrderDetail> findByOrderUserIdUserOrderByGoodName(long idBrand);

    Page<OrderDetail> findByOrderUserIdUserOrderByGoodName(long idBrand, Pageable pageable);


}