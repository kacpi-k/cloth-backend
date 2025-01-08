package dev.kkoncki.cloth.product.color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Color {
    private String id;
    private String title;
    private int red;
    private int green;
    private int blue;
    private String productId;
}
