package dev.kkoncki.cloth.auth.security;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.security.JwtAuthUser;
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserManagementRepository repository;

    public CustomUserDetailsService(UserManagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = repository.get(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        return JwtAuthUser.builder()
                .id(user.getId())
                .build();
    }
}
