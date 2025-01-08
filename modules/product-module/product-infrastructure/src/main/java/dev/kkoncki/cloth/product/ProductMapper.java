package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.ColorEntity;
import dev.kkoncki.cloth.product.color.ColorMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toProductEntity(Product product) {

        return ProductEntity.builder()
                .id(product.getId())
                .title(product.getTitle())
                .categoryId(product.getCategoryId())
                .createdOn(product.getCreatedOn())
                .price(product.getPrice())
                .discountedPrice(product.getDiscountedPrice())
                .gender(product.getGender())
                .salesNumber(product.getSalesNumber())
                .sizes(product.getSizes())
                .images(product.getImages())
                .build();
    }

    public static Product toProduct(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .categoryId(productEntity.getCategoryId())
                .createdOn(productEntity.getCreatedOn())
                .price(productEntity.getPrice())
                .discountedPrice(productEntity.getDiscountedPrice())
                .gender(productEntity.getGender())
                .salesNumber(productEntity.getSalesNumber())
                .sizes(productEntity.getSizes())
                .images(productEntity.getImages())
                .build();
    }
}
