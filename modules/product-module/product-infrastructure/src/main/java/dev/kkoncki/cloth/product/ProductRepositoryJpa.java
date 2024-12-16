package dev.kkoncki.cloth.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

    List<ProductEntity> findByCreatedOnGreaterThanEqual(Instant createdOn);
    List<ProductEntity> findBySalesNumberGreaterThan(int salesNumber);
    Optional<ProductEntity> findByCategoryId(String categoryId);
}
