package dev.kkoncki.cloth.user.management.repository;

import dev.kkoncki.cloth.user.management.User;

import java.util.Optional;

public interface UserManagementRepository {
    Optional<User> get(String id);
    User save(User user);
}
