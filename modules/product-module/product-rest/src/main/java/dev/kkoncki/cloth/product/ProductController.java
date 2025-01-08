package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.commons.LoggedUser;
import dev.kkoncki.cloth.product.forms.*;
import dev.kkoncki.cloth.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;
    private final LoggedUser loggedUser;

    public ProductController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/get-by-category-id/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable String categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/get-top-selling")
    public List<Product> getTopSelling() {
        return productService.getTopSelling();
    }

    @GetMapping("/get-new-in")
    public List<Product> getNewIn() {
        return productService.getNewIn();
    }

    @GetMapping("/get/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody CreateProductForm form) {
        return productService.save(form);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateProductForm form) {
        productService.update(form);
    }

    @PostMapping("/add-or-remove-favorite/{productId}")
    public void addOrRemoveFavoriteProduct(@PathVariable String productId) {
        productService.addOrRemoveFavoriteProduct(loggedUser.getUserId(), productId);
    }

    @GetMapping("/is-favorite/{productId}")
    public boolean isFavorite(@PathVariable String productId) {
        return productService.isFavorite(loggedUser.getUserId(), productId);
    }

    @GetMapping("/get-favorite-products")
    public List<Product> getFavoriteProducts() {
        return productService.getFavoriteProducts(loggedUser.getUserId());
    }

    @PutMapping("/sell")
    public void sellProduct(@RequestBody SellProductForm form) {
        productService.sellProduct(form);
    }

    @PutMapping("/update-price")
    public void updatePrice(@RequestBody UpdateProductPriceForm form) {
        productService.updatePrice(form);
    }

    @PutMapping("/add-discount")
    public void addDiscount(@RequestBody AddProductDiscountForm form) {
        productService.addDiscount(form);
    }
}
