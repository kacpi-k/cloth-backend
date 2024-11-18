package dev.kkoncki.cloth.auth;

import dev.kkoncki.cloth.auth.forms.CreateUserWithPasswordForm;
import dev.kkoncki.cloth.auth.forms.LoginForm;
import dev.kkoncki.cloth.auth.interfaces.AuthUserProps;
import dev.kkoncki.cloth.auth.interfaces.PasswordEncoder;
import dev.kkoncki.cloth.auth.interfaces.TokenProvider;
import dev.kkoncki.cloth.auth.repository.AuthRepository;
import dev.kkoncki.cloth.auth.service.AuthService;
import dev.kkoncki.cloth.auth.service.AuthServiceImpl;
import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import dev.kkoncki.cloth.user.management.service.UserManagementServiceImpl;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    AuthRepository authRepository = new AuthRepositoryMock();
    PasswordEncoder encoder = new PasswordEncoderMock();
    TokenProvider provider = new TokenProviderMock();
    AuthUserProps props = new AuthUserPropsMock();

    UserManagementRepository userManagementRepository = new UserManagementRepositoryMock();
    UserManagementService userManagementService = new UserManagementServiceImpl(userManagementRepository);

    AuthService authService = new AuthServiceImpl(authRepository, encoder, provider, userManagementService, props);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    private <T> void genericViolationSet(T form) {
        Set<ConstraintViolation<T>> violations = validator.validate(form);

        assertThrows(ConstraintViolationException.class, () -> {
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed", violations);
            }
        });
    }

    @BeforeEach
    void setup() {
        CreateUserWithPasswordForm form = new CreateUserWithPasswordForm();
        form.setFirstName("Kacper");
        form.setLastName("Koncki");
        form.setEmail("test@test.pl");
        form.setPassword("password123");
        authService.createUser(form);
    }

    @Test
    void successfulLoginTesT() {
        LoginResponse response = authService.login(new LoginForm("test@test.pl", "password123"));

        assertNotNull(response);
        assertNotNull(response.getToken());
        assertEquals("Bearer", response.getType());
    }

    @Test
    void userNotFountTest() {
        assertThrows(RuntimeException.class, () -> authService.login(new LoginForm("wrongemail@test.pl", "password123")));
    }

    @Test
    void incorrectPasswordTest() {
        assertThrows(RuntimeException.class, () -> authService.login(new LoginForm("test@test.pl", "wrongpassword")));
    }

    @Test
    void validateCreateUserWithPasswordForm() {
        CreateUserWithPasswordForm form = new CreateUserWithPasswordForm("pass");

        genericViolationSet(form);
    }

    @Test
    void validateLoginForm() {
        LoginForm form = new LoginForm("test", "pass");

        genericViolationSet(form);
    }
}
