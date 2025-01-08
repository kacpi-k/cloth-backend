package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.CreateProductForm;
import dev.kkoncki.cloth.product.forms.UpdateProductForm;
import dev.kkoncki.cloth.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getTopSelling() {
        int salesNumber = 30;
        return productRepository.findTopSelling(salesNumber);
    }

    @Override
    public List<Product> getNewIn() {
        Instant thirtyDaysAgo = Instant.now().minus(30, ChronoUnit.DAYS);
        return productRepository.findNewIn(thirtyDaysAgo);
    }

    private Product getOrThrowProductById(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product getProductById(String productId) {
        return getOrThrowProductById(productId);
    }

    @Override
    public Product save(CreateProductForm form) {
        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .title(form.getTitle())
                .categoryId(form.getCategoryId())
                .createdOn(Instant.now())
                .price(form.getPrice())
                .discountedPrice(form.getDiscountedPrice()) // TODO maybe add null and make method to add discount
                .gender(form.getGender())
                .salesNumber(0)
                .sizes(form.getSizeList())
                .images(form.getImageList())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(UpdateProductForm form) {
        Product existingProduct = getOrThrowProductById(form.getId());

        existingProduct.setSizes(form.getSizeList());
        existingProduct.setImages(form.getImageList());

        return productRepository.save(existingProduct);
    }

    @Override
    public void addOrRemoveFavoriteProduct(String userId, String productId) {

    }

    @Override
    public boolean isFavorite(String userId, String productId) {
        return false;
    }

    @Override
    public List<Product> getFavoriteProducts(String userId) {
        return List.of();
    }

    @Override
    public void sellProduct(String productId, int quantity) {
        Product product = getProductById(productId);
        product.setSalesNumber(product.getSalesNumber() + quantity);
        productRepository.save(product);
    }

    @Override
    public void updatePrice(String productId, double newPrice) {
        Product product = getProductById(productId);
        product.setPrice(newPrice);
        productRepository.save(product);
    }

    @Override
    public void addDiscount(String productId, double discountedPrice) {
        Product product = getProductById(productId);
        product.setDiscountedPrice(discountedPrice);
        productRepository.save(product);
    }
}
