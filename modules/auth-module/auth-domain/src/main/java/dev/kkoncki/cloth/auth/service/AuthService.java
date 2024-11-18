package dev.kkoncki.cloth.auth.service;

import dev.kkoncki.cloth.auth.LoginResponse;
import dev.kkoncki.cloth.auth.forms.CreateUserWithPasswordForm;
import dev.kkoncki.cloth.auth.forms.LoginForm;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthService {

    LoginResponse login(@Valid LoginForm logInRequest);

    void createUser(@Valid CreateUserWithPasswordForm createUserRequest);
}
