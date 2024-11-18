package dev.kkoncki.cloth.auth;

import dev.kkoncki.cloth.auth.forms.CreateUserWithPasswordForm;
import dev.kkoncki.cloth.auth.forms.LoginForm;
import dev.kkoncki.cloth.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginForm form) {
        return authService.login(form);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody CreateUserWithPasswordForm form) {
        authService.createUser(form);
    }
}
