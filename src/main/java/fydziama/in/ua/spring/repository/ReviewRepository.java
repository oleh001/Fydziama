package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //findBy (Review.status | User.name) - IgnoreCase - OrderBy
    List<Review> findByReviewContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByUserName(String review, String name);

    Page<Review> findByReviewContainingIgnoreCaseOrUserNameContainingIgnoreCaseOrderByUserName(String review, String name, Pageable pageable);

    List<Review> findByGoodIdGoodOrderByReviewDateDesc(long idGood);

    Page<Review> findByGoodIdGoodOrderByReviewDateDesc(long idGood, Pageable pageable);

}