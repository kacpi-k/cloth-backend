package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.repository.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

public class CategoryRepositoryMock implements CategoryRepository {

    Map<String, Category> mockDB = new HashMap<>();

    @Override
    public Category save(Category category) {
        mockDB.put(category.getId(), category);
        return category;
    }
}
