package dev.kkoncki.cloth.product.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductForm {
    @NotNull(message = "ID cannot be null.")
    @Size(min = 36, max = 36, message = "ID must have 36 characters.")
    private String id;

    @NotEmpty(message = "Sizes list cannot be empty")
    @NotNull(message = "Sizes list cannot be null")
    private List<String> sizeList;

    @NotEmpty(message = "Images list cannot be empty")
    @NotNull(message = "Images list cannot be null")
    private List<String> imageList;
}
