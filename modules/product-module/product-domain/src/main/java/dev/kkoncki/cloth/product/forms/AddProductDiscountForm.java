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
public class AddProductDiscountForm {

    @NotNull(message = "ProductId cannot be null.")
    @Size(min = 36, max = 36, message = "ID must have 36 characters.")
    private String productId;

    @Min(value = 0, message = "Discounted price must be greater than or equal to 0")
    private double discountedPrice;
}
