package community.dto.user;

import lombok.*;

public class ArticleCategoryDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleCategoryResponseDto {

        private Long id;

        private Long categoryId;

        private Long articleId;




    }
}
