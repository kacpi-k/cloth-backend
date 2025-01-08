package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.CreateProductForm;
import dev.kkoncki.cloth.product.forms.UpdateProductForm;
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
    Product update(@Valid UpdateProductForm form);
    void addOrRemoveFavoriteProduct(String userId, String productId); //TODO
    boolean isFavorite(String userId, String productId);//TODO
    List<Product> getFavoriteProducts(String userId); //TODO
    void sellProduct(String productId, int quantity);
    void updatePrice(String productId, double newPrice);
    void addDiscount(String productId, double discountedPrice);
}
