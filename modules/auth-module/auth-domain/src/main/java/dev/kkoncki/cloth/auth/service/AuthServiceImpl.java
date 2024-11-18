package dev.kkoncki.cloth.auth.service;

import dev.kkoncki.cloth.auth.AuthUser;
import dev.kkoncki.cloth.auth.LoginResponse;
import dev.kkoncki.cloth.auth.forms.CreateUserWithPasswordForm;
import dev.kkoncki.cloth.auth.forms.LoginForm;
import dev.kkoncki.cloth.auth.interfaces.AuthUserProps;
import dev.kkoncki.cloth.auth.interfaces.PasswordEncoder;
import dev.kkoncki.cloth.auth.interfaces.TokenProvider;
import dev.kkoncki.cloth.auth.repository.AuthRepository;
import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserManagementService userManagementService;
    private final AuthUserProps props;

    public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, UserManagementService userManagementService, AuthUserProps props) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userManagementService = userManagementService;
        this.props = props;
    }

    private AuthUser getByEmailOrThrowAuthUser(String email) {
        return authRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public LoginResponse login(LoginForm logInRequest) {

        AuthUser user = getByEmailOrThrowAuthUser(logInRequest.getEmail());

        if(passwordEncoder.match(logInRequest.getPassword(), user.getPassword())) {
            String token  = tokenProvider.generateToken(user.getId());
            return new LoginResponse(token);
        }
        throw new ApplicationException(ErrorCode.WRONG_LOGIN_OR_PASSWORD);
    }

    @Override
    public void createUser(CreateUserWithPasswordForm createUserRequest) {

        User user = userManagementService.save(createUserRequest);

        AuthUser authUser = AuthUser.builder()
                .id(user.getId())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();

        authRepository.saveAuthUser(authUser);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void createSysAdminOnStartup() {

        if (authRepository.findByEmail(props.getSysAdminEmail()).isPresent()) {
            log.info("Method createSysAdminOnStartup() - SysAdmin already exists");
            return;
        }

        User user = userManagementService.saveSysAdminOnStartup(new CreateUserForm(props.getSysAdminFirstName(), props.getSysAdminLastName(), props.getSysAdminEmail(), 1));

        AuthUser authUser = AuthUser.builder()
                .id(user.getId())
                .email(props.getSysAdminEmail())
                .password(passwordEncoder.encode(props.getSysAdminPassword()))
                .build();

        authRepository.saveAuthUser(authUser);
    }
}
