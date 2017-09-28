package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Faq;
import fydziama.in.ua.entity.GoodVisible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByStepOrderByPosition(GoodVisible goodVisible);

    Page<Faq> findByStepOrderByPosition(GoodVisible goodVisible, Pageable pageable);
}
