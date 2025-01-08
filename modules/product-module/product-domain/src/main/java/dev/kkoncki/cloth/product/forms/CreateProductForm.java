package dev.kkoncki.cloth.product.forms;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductForm {

    @NotBlank(message = "Title must not be blank")
    @Length(min = 2, message = "Title must be at least 2 characters long")
    private String title;

    @NotNull(message = "CategoryId cannot be null.")
    @Size(min = 36, max = 36, message = "ID must have 36 characters.")
    private String categoryId;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Discounted price must be greater than or equal to 0")
    private double discountedPrice;

    @Min(value = 1, message = "Gender must be 1 or 2")
    @Max(value = 2, message = "Gender must be 1 or 2")
    private int gender;

    @NotEmpty(message = "Sizes list cannot be empty")
    @NotNull(message = "Sizes list cannot be null")
    private List<String> sizeList;

    @NotEmpty(message = "Images list cannot be empty")
    @NotNull(message = "Images list cannot be null")
    private List<String> imageList;
}
