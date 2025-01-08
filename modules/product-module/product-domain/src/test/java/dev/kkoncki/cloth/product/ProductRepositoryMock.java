package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.repository.ProductRepository;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryMock implements ProductRepository {

    Map<String, Product> mockDB = new HashMap<>();

    @Override
    public Product save(Product product) {
        mockDB.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(mockDB.get(id));
    }

    @Override
    public List<Product> findNewIn(Instant thresholdDate) {
        return mockDB.values().stream()
                .filter(product -> product.getCreatedOn().isAfter(thresholdDate))
                .toList();
    }

    @Override
    public List<Product> findTopSelling(int salesNumber) {
        return mockDB.values().stream()
                .filter(product -> product.getSalesNumber() > salesNumber)
                .toList();
    }

    @Override
    public List<Product> findByCategoryId(String categoryId) {
        return mockDB.values().stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .toList();
    }
}
