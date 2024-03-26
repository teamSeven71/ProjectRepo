package community.repository;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    //List<Article> findByTitle(String title);
    @Modifying
    @Query("update ArticleEntity set title= :title where id = :id")
    int updateTitle(Long id, String title);

}