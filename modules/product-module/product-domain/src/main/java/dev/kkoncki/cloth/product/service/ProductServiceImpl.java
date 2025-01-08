package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.CreateProductForm;
import dev.kkoncki.cloth.product.forms.UpdateProductForm;
import dev.kkoncki.cloth.product.repository.ProductRepository;
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserManagementService userManagementService;

    public ProductServiceImpl(ProductRepository productRepository, UserManagementService userManagementService) {
        this.productRepository = productRepository;
        this.userManagementService = userManagementService;
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
    public void update(UpdateProductForm form) {
        Product existingProduct = getProductById(form.getId());

        existingProduct.setSizes(form.getSizeList());
        existingProduct.setImages(form.getImageList());

        productRepository.save(existingProduct);
    }

    @Override
    public void addOrRemoveFavoriteProduct(String userId, String productId) {
        User user = userManagementService.get(userId);
        if (user.getFavoriteProductsIds().contains(productId)) {
            user.getFavoriteProductsIds().remove(productId);
        } else {
            user.getFavoriteProductsIds().add(productId);
        }
        userManagementService.saveFavoriteProduct(user);
    }

    @Override
    public boolean isFavorite(String userId, String productId) {
        User user = userManagementService.get(userId);
        return user.getFavoriteProductsIds().contains(productId);
    }

    @Override
    public List<Product> getFavoriteProducts(String userId) {
        User user = userManagementService.get(userId);
        List<Product> products = productRepository.findByIds(user.getFavoriteProductsIds());
        if (!products.isEmpty()) {
            return products;
        } else {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        }

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
