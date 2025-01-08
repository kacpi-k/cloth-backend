package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.forms.CreateCategoryForm;
import dev.kkoncki.cloth.product.category.repository.CategoryRepository;
import dev.kkoncki.cloth.product.category.service.CategoryService;
import dev.kkoncki.cloth.product.category.service.CategoryServiceImpl;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {

    CategoryRepository repository = new CategoryRepositoryMock();
    CategoryService service = new CategoryServiceImpl(repository);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void saveTest() {
        Category category = service.save(new CreateCategoryForm("Shoes", "shoes"));
        assertNotNull(category.getId());
        assertEquals(category.getTitle(), "Shoes");
        assertEquals(category.getImageUrl(), "shoes");
    }

    @Test
    void validateCreateCategoryForm() {
        CreateCategoryForm createCategoryForm = new CreateCategoryForm("S", "s");

        Set<ConstraintViolation<CreateCategoryForm>> violations = validator.validate(createCategoryForm);

        assertThrows(ConstraintViolationException.class, () -> {
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed",violations);
            }
        });
    }
}
