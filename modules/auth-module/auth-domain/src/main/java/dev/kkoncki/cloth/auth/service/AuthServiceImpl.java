package dev.kkoncki.cloth.auth.service;

import dev.kkoncki.cloth.auth.LoginResponse;
import dev.kkoncki.cloth.auth.forms.CreateUserWithPasswordForm;
import dev.kkoncki.cloth.auth.forms.LoginForm;
import dev.kkoncki.cloth.auth.interfaces.AuthUserProps;
import dev.kkoncki.cloth.auth.interfaces.PasswordEncoder;
import dev.kkoncki.cloth.auth.interfaces.TokenProvider;
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserManagementService userManagementService;
    private final AuthUserProps props;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, TokenProvider tokenProvider, UserManagementService userManagementService, AuthUserProps props) {
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userManagementService = userManagementService;
        this.props = props;
    }

    @Override
    public LoginResponse login(LoginForm logInRequest) {
        return null;
    }

    @Override
    public void createUser(CreateUserWithPasswordForm createUserRequest) {

    }

    @Override
    public void logout(String userId) {
        User user = userManagementService.get(userId);
    }
}
