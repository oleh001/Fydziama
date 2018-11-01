package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Banner;
import fydziama.in.ua.entity.BannerType;
import fydziama.in.ua.entity.Visible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findByTypeEqualsAndVisibilityEquals(BannerType type,Visible bool);
}
