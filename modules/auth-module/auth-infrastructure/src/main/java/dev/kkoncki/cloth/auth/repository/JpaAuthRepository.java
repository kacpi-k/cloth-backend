package dev.kkoncki.cloth.auth.repository;

import dev.kkoncki.cloth.auth.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAuthRepository extends JpaRepository<AuthUserEntity, String> {
    Optional<AuthUserEntity> findByEmail(String email);
}
