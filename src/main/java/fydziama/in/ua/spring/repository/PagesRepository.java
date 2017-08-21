package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Pages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagesRepository extends JpaRepository<Pages, Long> {
    //findBy (Order.status | User.name) - IgnoreCase - OrderBy
    List<Pages> findByTitleContainingIgnoreCaseOrderByTitle(String title);

    Page<Pages> findByTitleContainingIgnoreCaseOrderByTitle(String title, Pageable pageable);
}