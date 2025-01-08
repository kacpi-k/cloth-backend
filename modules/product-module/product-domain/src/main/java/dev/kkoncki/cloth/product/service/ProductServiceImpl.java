package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.*;
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
    public void sellProduct(SellProductForm form) {
        Product product = getProductById(form.getProductId());
        product.setSalesNumber(product.getSalesNumber() + form.getQuantity());
        productRepository.save(product);
    }

    @Override
    public void updatePrice(UpdateProductPriceForm form) {
        Product product = getProductById(form.getProductId());
        product.setPrice(form.getNewPrice());
        productRepository.save(product);
    }

    @Override
    public void addDiscount(AddProductDiscountForm form) {
        Product product = getProductById(form.getProductId());
        if(product.getPrice() < form.getDiscountedPrice()) {
            throw new ApplicationException(ErrorCode.DISCOUNTED_PRICE_HIGHER_THAN_PRICE);
        }
        product.setDiscountedPrice(form.getDiscountedPrice());
        productRepository.save(product);
    }
}
