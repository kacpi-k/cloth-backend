package dev.kkoncki.cloth.user.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserManagementRepository extends JpaRepository<UserEntity, String> {
}
