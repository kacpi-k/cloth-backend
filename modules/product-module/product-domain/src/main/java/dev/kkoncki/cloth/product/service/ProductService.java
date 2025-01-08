package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductService {
    //List<Product> getProductsByTitle(String title); // TODO search
    List<Product> getProductsByCategoryId(String categoryId);
    List<Product> getTopSelling();
    List<Product> getNewIn();
    Product getProductById(String productId);
    Product save(@Valid CreateProductForm form);
    void update(@Valid UpdateProductForm form);
    void addOrRemoveFavoriteProduct(String userId, String productId);
    boolean isFavorite(String userId, String productId);
    List<Product> getFavoriteProducts(String userId);
    void sellProduct(@Valid SellProductForm form);
    void updatePrice(@Valid UpdateProductPriceForm form);
    void addDiscount(@Valid AddProductDiscountForm form);
}
