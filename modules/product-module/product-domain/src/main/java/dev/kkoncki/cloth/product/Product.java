package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.Color;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String id;
    private String title;
    private String categoryId;
    private Instant createdOn;
    private double price;
    private double discountedPrice;
    private int gender;
    private int salesNumber;
    private List<String> sizes;
    private List<String> images;
    private List<Color> colors;
}
