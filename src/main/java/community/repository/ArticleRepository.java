package community.repository;

import community.constant.CategoryType;
import community.domain.user.ArticleEntity;
import community.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

//    @Query("select a from ArticleEntity a where a.type = :type")
//    List<ArticleEntity> findAllCategory(CategoryType type);



}