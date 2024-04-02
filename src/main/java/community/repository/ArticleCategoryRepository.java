package community.repository;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategoryEntity, Long> {
    // 기본적으로 JpaRepository가 제공하는 메서드 외에 필요한 추가 메서드가 있다면 여기에 선언할 수 있습니다.

    @Query("select a from ArticleCategoryEntity a where a.category.categoryName = :type")
    List<ArticleCategoryEntity> findAllArticleByCategory(String type);
}
