package dev.kkoncki.cloth.product.repository;

import dev.kkoncki.cloth.product.Product;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> findNewIn(Instant thresholdDate);
    List<Product> findTopSelling(int salesNumber);
    List<Product> findByCategoryId(String categoryId);
}
