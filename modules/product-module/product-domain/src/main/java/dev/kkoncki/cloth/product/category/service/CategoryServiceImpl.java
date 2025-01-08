package dev.kkoncki.cloth.product.category.service;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.forms.CreateCategoryForm;
import dev.kkoncki.cloth.product.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CreateCategoryForm form) {
        Category category = Category.builder()
                .id(UUID.randomUUID().toString())
                .title(form.getTitle())
                .imageUrl(form.getImage())
                .build();
        return categoryRepository.save(category);
    }
}
