package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.color.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
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