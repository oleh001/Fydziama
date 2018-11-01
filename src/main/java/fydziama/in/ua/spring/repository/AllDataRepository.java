package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.AllData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllDataRepository extends JpaRepository<AllData, Long> {
}
