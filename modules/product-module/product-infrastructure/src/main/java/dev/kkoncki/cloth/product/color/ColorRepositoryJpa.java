package dev.kkoncki.cloth.product.color;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepositoryJpa extends JpaRepository<ColorEntity, String>, JpaSpecificationExecutor<ColorEntity> {
}
