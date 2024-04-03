package community.repository;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategoryEntity, Long> {

    // 해당 카테고리 id 타입 가져와서 비교
    @Query("select a from ArticleCategoryEntity a where a.category.id = :categoryId")
    List<ArticleCategoryEntity> findAllArticleByCategory(Long categoryId);

    @Query("select a from ArticleCategoryEntity a where a.article.id = :articleId")
    List<ArticleCategoryEntity> getCategoriesById(Long articleId);

    @Modifying
    @Query("DELETE FROM ArticleCategoryEntity a WHERE a.article.id = :articleId")
    void deleteArticleInCategory(Long articleId);

}
