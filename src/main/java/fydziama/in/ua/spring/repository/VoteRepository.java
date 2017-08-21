package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    //findBy (Vote.value | Good.name | Brand.brand) - IgnoreCase - OrderBy
    List<Vote> findByGoodNameContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByGoodName(String name, String brand);

    Page<Vote> findByGoodNameContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByGoodName(String name, String brand, Pageable pageable);
}