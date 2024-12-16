package dev.kkoncki.cloth.product.color;

import dev.kkoncki.cloth.product.ProductEntity;

public class ColorMapper {

    public static ColorEntity toColorEntity(Color color, ProductEntity productEntity) {
        return ColorEntity.builder()
                .id(color.getId())
                .title(color.getTitle())
                .red(color.getRed())
                .green(color.getGreen())
                .blue(color.getBlue())
                .product(productEntity)
                .build();
    }

    public static Color toColor(ColorEntity colorEntity) {
        return Color.builder()
                .id(colorEntity.getId())
                .title(colorEntity.getTitle())
                .red(colorEntity.getRed())
                .green(colorEntity.getGreen())
                .blue(colorEntity.getBlue())
                .productId(colorEntity.getProduct().getId())
                .build();
    }
}

