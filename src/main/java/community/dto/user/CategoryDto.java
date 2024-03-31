package community.dto.user;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.ArticleEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryResponseDto {

        private Long id;

        private String categoryName;

//        private List<ArticleCategoryEntity> articleCategories;


    }
}
