package dev.kkoncki.cloth.product;


import dev.kkoncki.cloth.product.color.Color;
import dev.kkoncki.cloth.product.color.forms.CreateColorForm;
import dev.kkoncki.cloth.product.color.repository.ColorRepository;
import dev.kkoncki.cloth.product.color.service.ColorService;
import dev.kkoncki.cloth.product.color.service.ColorServiceImpl;
import dev.kkoncki.cloth.product.forms.CreateProductForm;
import dev.kkoncki.cloth.product.repository.ProductRepository;
import dev.kkoncki.cloth.product.service.ProductService;
import dev.kkoncki.cloth.product.service.ProductServiceImpl;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ColorServiceTest {
    ColorRepository colorRepository = new ColorRepositoryMock();
    ProductRepository productRepository = new ProductRepositoryMock();
    ProductService productService = new ProductServiceImpl(productRepository);
    ColorService colorService = new ColorServiceImpl(colorRepository, productService);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();


    @Test
    void saveTest() {

        CreateProductForm createProductForm = new CreateProductForm(
                "Sample Product",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        Product product = productService.save(createProductForm);

        CreateColorForm createColorForm = new CreateColorForm("Test Color", 255, 123, 21, product.getId());

        Color color = colorService.save(createColorForm);

        assertNotNull(color.getId());
        assertEquals(color.getTitle(), "Test Color");
        assertEquals(color.getRed(), 255);
        assertEquals(color.getGreen(), 123);
        assertEquals(color.getBlue(), 21);
        assertEquals(color.getProductId(), product.getId());
    }

    @Test
    void validateCreateColorForm() {
        CreateColorForm createColorForm = new CreateColorForm("Awesome Color", 255, 123, 21, "1");

        Set<ConstraintViolation<CreateColorForm>> violations = validator.validate(createColorForm);

        assertThrows(ConstraintViolationException.class, () -> {
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed",violations);
            }
        });
    }
}
