package dev.kkoncki.cloth.product.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductPriceForm {

    @NotNull(message = "Product ID cannot be null.")
    @Size(min = 36, max = 36, message = "ID must have 36 characters.")
    private String productId;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be greater than 0")
    private double newPrice;
}
