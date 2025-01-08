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
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import dev.kkoncki.cloth.user.management.service.UserManagementServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    ProductRepository productRepository = new ProductRepositoryMock();
    UserManagementRepository userManagementRepository = new UserManagementRepositoryMock();
    UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);
    ProductService productService = new ProductServiceImpl(productRepository, userManagementService);
    CategoryRepository categoryRepository = new CategoryRepositoryMock();
    CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    private Category defaultCategory;

    @BeforeEach
    void setup() {
        defaultCategory = categoryService.save(new CreateCategoryForm("Default Category", "image.jpg"));
    }

    @AfterEach
    void clear() {
        ((ProductRepositoryMock) productRepository).clear();
        ((UserManagementRepositoryMock) userManagementRepository).clear();
        ((CategoryRepositoryMock) categoryRepository).clear();
    }

    private Product createAndSaveProduct(String title, double price, double discountedPrice, int gender, List<String> sizes, List<String> images) {
        CreateProductForm form = new CreateProductForm(
                title,
                defaultCategory.getId(),
                price,
                discountedPrice,
                gender,
                sizes,
                images
        );
        return productService.save(form);
    }

    private Product createAndSaveProductWithCategory(String title, String categoryId ,double price, double discountedPrice, int gender, List<String> sizes, List<String> images) {
        CreateProductForm form = new CreateProductForm(
                title,
                categoryId,
                price,
                discountedPrice,
                gender,
                sizes,
                images
        );
        return productService.save(form);
    }

    private User createAndSaveUser(String firstName, String lastName, String email, int gender) {
        CreateUserForm form = new CreateUserForm(
                firstName,
                lastName,
                email,
                gender
        );

        return userManagementService.save(form);
    }

    @Test
    void getProductByIdTest() {
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

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
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        assertNotNull(product.getId());
        assertEquals(product.getTitle(), "Sample Product");
        assertEquals(product.getCategoryId(), defaultCategory.getId());
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
        createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        createAndSaveProductWithCategory(
                "Sample Product 2",
                category.getId(),
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image3.jpg", "image4.jpg")
        );
        createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image2.jpg", "image4.jpg")
        );

        List<Product> products = productService.getProductsByCategoryId(defaultCategory.getId());

        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
    }

    @Test
    void updateTest() {
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

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
        Product product1 = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        Product product2 = createAndSaveProduct(
                "Sample Product 2",
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.sellProduct(product1.getId(), 31);
        productService.sellProduct(product2.getId(), 29);

        List<Product> products = productService.getTopSelling();

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(product1.getId(), products.get(0).getId());
    }

    @Test
    void getNewInTest() {
        createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );
        Product product2 = createAndSaveProduct(
                "Sample Product 2",
                120.0,
                110.0,
                1,
                List.of("M", "L", "XL"),
                List.of("image1.jpg", "image2.jpg")
        );
        createAndSaveProduct(
                "Sample Product 2",
                90.0,
                75.0,
                1,
                List.of("S", "M", "L"),
                List.of("image2.jpg", "image4.jpg")
        );

        product2.setCreatedOn(Instant.now().minus(40, ChronoUnit.DAYS));

        List<Product> products = productService.getNewIn();

        assertFalse(products.isEmpty());
        assertEquals(2, products.size());
        assertTrue(products.stream().allMatch(product -> product.getCreatedOn().isAfter(product2.getCreatedOn())));
    }

    @Test
    void sellProductTest() {
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.sellProduct(product.getId(), 10);

        Product productAfterSale = productService.getProductById(product.getId());

        assertEquals(10, productAfterSale.getSalesNumber());
    }

    @Test
    void updatePriceTest() {
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.updatePrice(product.getId(), 90.0);

        Product productAfterUpdate = productService.getProductById(product.getId());

        assertEquals(90.0, productAfterUpdate.getPrice());
    }

    @Test
    void addDiscountPriceTest() {
        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.addDiscount(product.getId(), 70.0);

        Product productAfterUpdate = productService.getProductById(product.getId());

        assertEquals(100.0, productAfterUpdate.getPrice());
        assertEquals(70.0, productAfterUpdate.getDiscountedPrice());
    }

    @Test
    void addOrRemoveFavoriteProductTest() {
        User user = createAndSaveUser(
                "TestName",
                "TestLastName",
                "test@test.pl",
                1
        );

        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.addOrRemoveFavoriteProduct(user.getId(), product.getId());

        List<Product> favoriteProducts = productService.getFavoriteProducts(user.getId());

        assertFalse(user.getFavoriteProductsIds().isEmpty());
        assertTrue(favoriteProducts.stream().allMatch(favoriteProduct -> favoriteProduct.getId().equals(product.getId())));
    }

    @Test
    void isFavoriteTest() {
        User user = createAndSaveUser(
                "TestName",
                "TestLastName",
                "test@test.pl",
                1
        );

        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.addOrRemoveFavoriteProduct(user.getId(), product.getId());

        boolean isFavorite = productService.isFavorite(user.getId(), product.getId());

        assertTrue(isFavorite);
    }

    @Test
    void getFavoriteProductsTest() {
        User user = createAndSaveUser(
                "TestName",
                "TestLastName",
                "test@test.pl",
                1
        );

        Product product = createAndSaveProduct(
                "Sample Product",
                100.0,
                80.0,
                1,
                List.of("S", "M", "L"),
                List.of("image1.jpg", "image2.jpg")
        );

        productService.addOrRemoveFavoriteProduct(user.getId(), product.getId());

        List<Product> favoriteProducts = productService.getFavoriteProducts(user.getId());

        assertTrue(favoriteProducts.stream().allMatch(favoriteProduct -> favoriteProduct.getId().equals(product.getId())));
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
