package dev.kkoncki.cloth.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

    List<ProductEntity> findByCreatedOnGreaterThanEqual(Instant createdOn);
    List<ProductEntity> findBySalesNumberGreaterThan(int salesNumber);
    List<ProductEntity> findByCategoryId(String categoryId);
    List<ProductEntity> findByIdIn(List<String> favoriteProductsIds);
}
