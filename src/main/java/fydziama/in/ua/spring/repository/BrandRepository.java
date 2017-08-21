package fydziama.in.ua.spring.repository;

import fydziama.in.ua.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    // на основании имени метода будет построен Hibernate запрос
    List<Brand> findByBrandContainingIgnoreCaseOrderByBrand(String brand);

    Page<Brand> findByBrandContainingIgnoreCaseOrderByBrand(String brand, Pageable pageable);

}
