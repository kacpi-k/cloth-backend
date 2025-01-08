package dev.kkoncki.cloth.product.color.forms;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorForm {

    @NotBlank(message = "Title must not be blank")
    @Length(min = 2, message = "Title must be at least 2 characters long")
    private String title;

    @NotNull(message = "Red must not be null")
    @Min(value = 0, message = "Red value must be greater than or equal to 0")
    @Max(value = 255, message = "Red value must be less than or equal to 255")
    private int red;

    @NotNull(message = "Green must not be null")
    @Min(value = 0, message = "Green value must be greater than or equal to 0")
    @Max(value = 255, message = "Green value must be less than or equal to 255")
    private int green;

    @NotNull(message = "Blue must not be null")
    @Min(value = 0, message = "Blue value must be greater than or equal to 0")
    @Max(value = 255, message = "Blue value must be less than or equal to 255")
    private int blue;

    @NotNull(message = "ProductId cannot be null.")
    @Size(min = 36, max = 36, message = "ID must have 36 characters.")
    private String productId;
}
