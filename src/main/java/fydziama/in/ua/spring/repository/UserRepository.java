package fydziama.in.ua.spring.repository;


import fydziama.in.ua.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCaseOrderByName(String name);

    Page<User> findByNameContainingIgnoreCaseOrderByName(String name, Pageable pageable);

    User findByLoginIgnoreCaseAndPassword(String login, String password);

    @Query("select login FROM User")
    List<String> getUserLogin();

    @Query("select max(idUser) FROM User")
    Long getMaxUserId();
}