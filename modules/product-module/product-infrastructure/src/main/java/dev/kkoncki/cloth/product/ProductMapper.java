package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.ColorEntity;
import dev.kkoncki.cloth.product.color.ColorMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toProductEntity(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
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

        List<ColorEntity> colorEntities = product.getColors().stream()
                .map(color -> ColorMapper.toColorEntity(color, productEntity))
                .collect(Collectors.toList());

        productEntity.setColors(colorEntities);

        return productEntity;
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
                .colors(productEntity.getColors().stream()
                        .map(ColorMapper::toColor)
                        .collect(Collectors.toList()))
                .build();
    }
}
