package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Good;
import fydziama.in.ua.entity.GoodVisible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    // на основании имени метода будет построен Hibernate запрос
    List<Good> findByBrandIdBrand(long idBrand);

    Page<Good> findByBrandIdBrand(long idBrand, Pageable pageable);


    List<Good> findByNewsEquals(GoodVisible bool);
    Page<Good> findByNewsEquals(GoodVisible bool, Pageable pageable);

    List<Good> findBySpecialsEquals(GoodVisible bool);
    Page<Good> findBySpecialsEquals(GoodVisible bool, Pageable pageable);

    // на основании имени метода будет построен Hibernate запрос
    List<Good> findByNameContainingIgnoreCaseOrBrandBrandContainingIgnoreCaseOrderByName(String name, String brand);

    Page<Good> findByNameContainingIgnoreCaseOrBrandBrandContainingIgnoreCaseOrderByName(String name, String brand, Pageable pageable);

    // обновить данные рейтинга
    @Modifying
    @Query("update Good b set b.totalVoteCount=:totalVoteCount, b.totalRating=:totalRating, b.avgRating=:avgRating where b.idGood =:id")
    void updateRating(@Param("totalRating") long totalRating, @Param("totalVoteCount") long totalVoteCount, @Param("avgRating") int avgRating, @Param("id") long id);
}