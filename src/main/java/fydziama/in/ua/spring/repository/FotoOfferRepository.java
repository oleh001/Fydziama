package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.FotoOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoOfferRepository extends JpaRepository<FotoOffer, Long> {
}
