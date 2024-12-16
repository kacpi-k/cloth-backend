package dev.kkoncki.cloth.product.color;

import dev.kkoncki.cloth.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "color", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "red")
    private int red;

    @Column(name = "green")
    private int green;

    @Column(name = "blue")
    private int blue;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
