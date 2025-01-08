package dev.kkoncki.cloth.product.category;

import dev.kkoncki.cloth.product.category.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryAdapter implements CategoryRepository {
    private final CategoryRepositoryJpa categoryRepositoryJpa;

    public CategoryRepositoryAdapter(CategoryRepositoryJpa categoryRepositoryJpa) {
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    public Category save(Category category) {
        CategoryEntity categoryEntity = CategoryMapper.toCategoryEntity(category);
        categoryRepositoryJpa.save(categoryEntity);
        return CategoryMapper.toCategory(categoryEntity);
    }

}
