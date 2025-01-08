package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.forms.CreateCategoryForm;
import dev.kkoncki.cloth.product.category.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public Category save(@RequestBody CreateCategoryForm form) {
        return categoryService.save(form);
    }
}
