package dev.kkoncki.cloth.product.category.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryForm {

    @NotBlank(message = "Title must not be blank")
    @Length(min = 2, message = "Title must be at least 2 characters long")
    private String title;

    @NotBlank(message = "Image must not be blank")
    @Length(min = 2, message = "Image must be at least 2 characters long")
    private String image;
}
