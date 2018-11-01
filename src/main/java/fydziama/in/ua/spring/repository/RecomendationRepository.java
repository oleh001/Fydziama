package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Recomendation;
import fydziama.in.ua.entity.Visible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendationRepository extends JpaRepository<Recomendation, Long> {

    List<Recomendation> findByVisibilityEquals(Visible bool);

}
