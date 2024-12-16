package dev.kkoncki.cloth.product.service;

import dev.kkoncki.cloth.product.Product;
import dev.kkoncki.cloth.product.forms.CreateProductForm;

import java.util.List;

public interface ProductService {
    //List<Product> getProductsByTitle(String title); // TODO search
    List<Product> getProductsByCategoryId(String categoryId);
    List<Product> getTopSelling();
    List<Product> getNewIn();
    Product save(CreateProductForm form);
    void addOrRemoveFavoriteProduct(String userId, String productId); //TODO
    boolean isFavorite(String userId, String productId);//TODO
    List<Product> getFavoriteProducts(String userId); //TODO
}
