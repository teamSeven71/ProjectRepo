package community.repository;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.CommentEntity;
import community.dto.user.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategoryEntity, Long> {

   /* // 해당 카테고리 id 타입 가져와서 비교
    @Query("select a from ArticleCategoryEntity a where a.category.id = :categoryId")
    List<ArticleCategoryEntity> findAllArticleByCategory(Long categoryId);*/

    // 해당 카테고리 id 타입 가져와서 비교(pagination 사용 때문에)
    @Query("SELECT a FROM ArticleCategoryEntity a WHERE a.category.id = :categoryId")
    Page<ArticleCategoryEntity> findAllArticleByCategory(Long categoryId, Pageable pageable);
    // 해당 카테고리 id 타입 가져와서 비교(pagination 사용 때문에)
    @Query("SELECT a FROM ArticleCategoryEntity a WHERE a.category.id = :categoryId AND a.article.title LIKE %:title%")
    Page<ArticleCategoryEntity> findByTitleContainingIgnoreCase(String title, Long categoryId, Pageable pageable);

    // 해당 카테고리 id 타입 가져와서 비교(메인 페이지의 pagination 사용 x)
    @Query("select a from ArticleCategoryEntity a where a.category.id = :categoryId")
    List<ArticleCategoryEntity> getAllArticleByCategory(Long categoryId);

    @Query("select a from ArticleCategoryEntity a where a.article.id = :articleId")
    List<ArticleCategoryEntity> getCategoriesById(Long articleId);

    @Modifying
    @Query("DELETE FROM ArticleCategoryEntity a WHERE a.article.id = :articleId")
    void deleteArticleInCategory(Long articleId);

}
