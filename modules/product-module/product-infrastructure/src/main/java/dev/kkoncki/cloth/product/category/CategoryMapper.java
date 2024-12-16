package dev.kkoncki.cloth.product.category;

public class CategoryMapper {

    public static CategoryEntity toCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
    }

    public static Category toCategory(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .title(categoryEntity.getTitle())
                .build();
    }
}
