package dev.kkoncki.cloth.product;

import dev.kkoncki.cloth.product.category.Category;
import dev.kkoncki.cloth.product.category.forms.CreateCategoryForm;
import dev.kkoncki.cloth.product.category.repository.CategoryRepository;
import dev.kkoncki.cloth.product.category.service.CategoryService;
import dev.kkoncki.cloth.product.category.service.CategoryServiceImpl;
import dev.kkoncki.cloth.product.forms.CreateProductForm;
import dev.kkoncki.cloth.product.forms.UpdateProductForm;
import dev.kkoncki.cloth.product.repository.ProductRepository;
import dev.kkoncki.cloth.product.service.ProductService;
import dev.kkoncki.cloth.product.service.ProductServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    ProductRepository productRepository = new ProductRepositoryMock();
    ProductService productService = new ProductServiceImpl(productRepository);
    CategoryRepository categoryRepository = new CategoryRepositoryMock();
    CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void getProductByIdTest() {
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

        Product productFound = productService.getProductById(product.getId());

        assertEquals(product.getId(), productFound.getId());
        assertEquals(product.getTitle(), productFound.getTitle());
        assertEquals(product.getCategoryId(), productFound.getCategoryId());
        assertEquals(product.getCreatedOn(), productFound.getCreatedOn());
        assertEquals(product.getPrice(), productFound.getPrice());
        assertEquals(product.getDiscountedPrice(), productFound.getDiscountedPrice());
        assertEquals(product.getGender(), productFound.getGender());
        assertEquals(product.getSalesNumber(), productFound.getSalesNumber());
        assertEquals(product.getSizes(), productFound.getSizes());
        assertEquals(product.getImages(), productFound.getImages());
    }

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

        assertNotNull(product.getId());
        assertEquals(product.getTitle(), "Sample Product");
        assertEquals(product.getCategoryId(), "123e4567-e89b-12d3-a456-426614174000");
        assertNotNull(product.getCreatedOn());
        assertEquals(product.getPrice(), 100.0);
        assertEquals(product.getDiscountedPrice(), 80.0);
        assertEquals(product.getGender(), 1);
        assertEquals(product.getSalesNumber(), 0);
        assertEquals(product.getSizes(), List.of("S", "M", "L"));
        assertEquals(product.getImages(), List.of("image1.jpg", "image2.jpg"));
    }

    @Test
    void getProductsByCategoryIdTest() {
        Category category = categoryService.save(new CreateCategoryForm("Test Category", "image.jpg"));

        CreateProductForm createProductForm1 = new CreateProductForm(
                "Sample Product 1",
                category.getId(),
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        CreateProductForm createProductForm2 = new CreateProductForm(
                "Sample Product 2",
                category.getId(),
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image3.jpg", "image4.jpg")
        );
        CreateProductForm createProductForm3 = new CreateProductForm(
                "Sample Product3",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        productService.save(createProductForm1);
        productService.save(createProductForm2);
        productService.save(createProductForm3);

        List<Product> products = productService.getProductsByCategoryId(category.getId());

        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }

    @Test
    void updateTest() {
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

        UpdateProductForm updateProductForm = new UpdateProductForm(
                product.getId(),
                List.of("S", "M", "L", "XL"),
                List.of("image1.jpg", "image2.jpg", "image3.jpg")
        );

        productService.update(updateProductForm);

        Product productUpdated = productService.getProductById(product.getId());

        assertEquals(product.getId(), productUpdated.getId());
        assertEquals(product.getTitle(), productUpdated.getTitle());
        assertEquals(product.getCategoryId(), productUpdated.getCategoryId());
        assertEquals(product.getCreatedOn(), productUpdated.getCreatedOn());
        assertEquals(product.getPrice(), productUpdated.getPrice());
        assertEquals(product.getDiscountedPrice(), productUpdated.getDiscountedPrice());
        assertEquals(product.getGender(), productUpdated.getGender());
        assertEquals(product.getSalesNumber(), productUpdated.getSalesNumber());
        assertEquals(product.getSizes(), productUpdated.getSizes());
        assertEquals(product.getImages(), productUpdated.getImages());
    }

    @Test
    void getTopSellingTest() {
        CreateProductForm createProductForm1 = new CreateProductForm(
                "Sample Product 1",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        CreateProductForm createProductForm2 = new CreateProductForm(
                "Sample Product 2",
                "123e4567-e89b-12d3-a456-426614174000",
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image3.jpg", "image4.jpg")
        );

        Product product1 = productService.save(createProductForm1);
        Product product2 = productService.save(createProductForm2);

        productService.sellProduct(product1.getId(), 31);
        productService.sellProduct(product2.getId(), 29);

        List<Product> products = productService.getTopSelling();
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(product1.getId(), products.get(0).getId());
    }

    @Test
    void getNewInTest() {
        CreateProductForm createProductForm1 = new CreateProductForm(
                "Sample Product 1",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        CreateProductForm createProductForm2 = new CreateProductForm(
                "Sample Product 2",
                "123e4567-e89b-12d3-a456-426614174000",
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image3.jpg", "image4.jpg")
        );
        CreateProductForm createProductForm3 = new CreateProductForm(
                "Sample Product3",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.save(createProductForm1);
        Product product2 = productService.save(createProductForm2);
        productService.save(createProductForm3);

        product2.setCreatedOn(Instant.now().minus(40, ChronoUnit.DAYS));

        List<Product> products = productService.getNewIn();

        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
        assertTrue(products.stream().allMatch(product -> product.getCreatedOn().isAfter(product2.getCreatedOn())));
    }

    @Test
    void sellProductTest() {
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

        productService.sellProduct(product.getId(), 10);

        Product productAfterSale = productService.getProductById(product.getId());

        assertEquals(10, productAfterSale.getSalesNumber());
    }

    @Test
    void updatePriceTest() {
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

        productService.updatePrice(product.getId(), 90.0);

        Product productAfterUpdate = productService.getProductById(product.getId());

        assertEquals(90.0, productAfterUpdate.getPrice());
    }

    @Test
    void addDiscountPrice() {
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

        productService.addDiscount(product.getId(), 70.0);

        Product productAfterUpdate = productService.getProductById(product.getId());

        assertEquals(100.0, productAfterUpdate.getPrice());
        assertEquals(70.0, productAfterUpdate.getDiscountedPrice());
    }

    @Test
    void validateCreateProductForm() {
        CreateProductForm createProductForm = new CreateProductForm(
                "Sample Product",
                "123e4567-e89b-12d3-a456-426614174000",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        Set<ConstraintViolation<CreateProductForm>> violations = validator.validate(createProductForm);

        assertTrue(violations.isEmpty());
    }

    @Test
    void validateUpdateProductForm() {
        UpdateProductForm updateProductForm = new UpdateProductForm(
                "123e4567-e89b-12d3-a456-426614174000",
                List.of("S", "M", "L", "XL"),
                List.of("image1.jpg", "image2.jpg", "image3.jpg")
        );

        Set<ConstraintViolation<UpdateProductForm>> violations = validator.validate(updateProductForm);

        assertTrue(violations.isEmpty());
    }
}
