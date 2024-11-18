package dev.kkoncki.cloth.auth;

import dev.kkoncki.cloth.auth.repository.AuthRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthRepositoryMock implements AuthRepository {

    Map<String, AuthUser> authUserMockDB = new HashMap<>();

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        return Optional.ofNullable(authUserMockDB.get(email));
    }

    @Override
    public Optional<AuthUser> findById(String id) {
        for (AuthUser user : authUserMockDB.values()) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void saveAuthUser(AuthUser user) {
        authUserMockDB.put(user.getEmail(), user);
    }
}
