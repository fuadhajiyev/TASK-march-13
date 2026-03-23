package az.company.training.repository;

import az.company.training.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA bizə save(), findById(), delete() kimi metodları hazır verir.
}