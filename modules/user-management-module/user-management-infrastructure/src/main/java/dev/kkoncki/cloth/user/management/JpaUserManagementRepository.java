package dev.kkoncki.cloth.user.management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserManagementRepository extends JpaRepository<UserEntity, String> {
}
