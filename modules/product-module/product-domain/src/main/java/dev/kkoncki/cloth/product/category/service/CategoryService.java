package dev.kkoncki.cloth.product.category.service;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.forms.CreateCategoryForm;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CategoryService {
    Category save(@Valid CreateCategoryForm form);
}
