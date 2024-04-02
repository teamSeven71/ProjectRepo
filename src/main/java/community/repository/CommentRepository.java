package community.repository;

import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("select c from ArticleEntity a join a.comments c where c.id = :commentId and a.id = :articleId")
    CommentEntity findCommentByIdAndArticleId(Long commentId, Long articleId);

    @Query("select c from ArticleEntity a join a.comments c where a.id = :articleId")
    List<CommentEntity> findByArticleId(Long articleId);

    @Query("select count(c) from ArticleEntity a join a.comments c where a.id = :articleId")
    Long countComment(Long articleId);

}

