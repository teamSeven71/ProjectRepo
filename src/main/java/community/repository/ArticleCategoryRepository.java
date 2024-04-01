package community.repository;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<CategoryEntity, Long> {


    List<ArticleCategoryEntity> findByCategoryId(Long categoryId);
}
