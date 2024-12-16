package dev.kkoncki.cloth.product.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryJpa extends JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity> {

}
