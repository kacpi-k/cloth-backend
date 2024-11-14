package dev.kkoncki.cloth.user.management;

import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.forms.EditUserForm;
import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import dev.kkoncki.cloth.user.management.service.UserManagementServiceImpl;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagementServiceTest {
    UserManagementRepository repository = new UserManagementRepositoryMock();
    UserManagementService service = new UserManagementServiceImpl(repository);

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void getTest() {
        User userSaved = service.save(new CreateUserForm("Kacper", "Koncki", "test@test.pl", 1));
        User userFound = service.get(userSaved.getId());
        assertEquals(userSaved.getId(), userFound.getId());
        assertEquals(userFound.getFirstName(), "Kacper");
        assertEquals(userFound.getLastName(), "Koncki");
        assertEquals(userFound.getEmail(), "test@test.pl");
        assertEquals(userFound.getGender(), 1);
    }

    @Test
    void saveTest() throws InterruptedException {
        Instant currentDate = Instant.now();
        Thread.sleep(2);
        User user = service.save(new CreateUserForm("Kacper", "Koncki", "test@test.pl", 1));
        Thread.sleep(2);
        Instant dateAfterSave = Instant.now();
        assertNotNull(user.getId());
        assertEquals(user.getFirstName(), "Kacper");
        assertEquals(user.getLastName(), "Koncki");
        assertEquals(user.getEmail(), "test@test.pl");
        assertEquals(user.getGender(), 1);
        assertTrue(currentDate.isBefore(user.getCreatedOn()));
        assertTrue(dateAfterSave.isAfter(user.getCreatedOn()));
    }

    @Test
    void validateCreateUserForm() {
        CreateUserForm createUserForm = new CreateUserForm("K", "T", "email", 0);

        Set<ConstraintViolation<CreateUserForm>> violations = validator.validate(createUserForm);

        assertThrows(ConstraintViolationException.class, () -> {
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed",violations);
            }
        });
    }

    @Test
    void updateTest() throws InterruptedException {
        Instant currentDate = Instant.now();
        Thread.sleep(2);
        User userSaved = service.save(new CreateUserForm("Kacper", "Koncki", "test@test.pl", 1));
        userSaved = service.update(new EditUserForm(userSaved.getId(), "Andrzej", "Kowalski", "test2@test.pl"));
        Thread.sleep(2);
        Instant dateAfterUpdate = Instant.now();
        assertEquals(userSaved.getFirstName(), "Andrzej");
        assertEquals(userSaved.getLastName(), "Kowalski");
        assertEquals(userSaved.getEmail(), "test2@test.pl");
        assertTrue(currentDate.isBefore(userSaved.getCreatedOn()));
        assertTrue(dateAfterUpdate.isAfter(userSaved.getCreatedOn()));
    }

    @Test
    void validateEditUserForm() {
        EditUserForm editUserForm = new EditUserForm(UUID.randomUUID().toString(), "K", "T", "email");

        Set<ConstraintViolation<EditUserForm>> violations = validator.validate(editUserForm);

        assertThrows(ConstraintViolationException.class, () -> {
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed", violations);
            }
        });
    }
}
