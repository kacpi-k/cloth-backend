package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductRepositoryJpa productRepositoryJpa;

    public ProductRepositoryAdapter(ProductRepositoryJpa productRepositoryJpa) {
        this.productRepositoryJpa = productRepositoryJpa;
    }


    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductMapper.toProductEntity(product);
        productRepositoryJpa.save(productEntity);
        return ProductMapper.toProduct(productEntity);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepositoryJpa.findById(id).map(ProductMapper::toProduct);
    }

    @Override
    public List<Product> findNewIn(Instant thresholdDate) {
        List<ProductEntity> productEntities = productRepositoryJpa.findByCreatedOnGreaterThanEqual(thresholdDate);
        return productEntities.stream()
                .map(ProductMapper::toProduct)
                .toList();
    }

    @Override
    public List<Product> findTopSelling() {
        List<ProductEntity> productEntities = productRepositoryJpa.findBySalesNumberGreaterThan(30);
        return productEntities.stream()
                .map(ProductMapper::toProduct)
                .toList();
    }

    @Override
    public Optional<Product> findByCategoryId(String categoryId) {
        return productRepositoryJpa.findByCategoryId(categoryId).map(ProductMapper::toProduct);
    }
}
