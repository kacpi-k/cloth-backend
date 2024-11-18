package dev.kkoncki.cloth.auth.repository;

import dev.kkoncki.cloth.auth.AuthUser;

import java.util.Optional;

public interface AuthRepository {
    Optional<AuthUser> findByEmail(String email);

    Optional<AuthUser> findById(String id);

    void saveAuthUser(AuthUser user);
}
