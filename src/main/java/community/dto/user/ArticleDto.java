package community.dto.user;

import community.constant.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ArticleDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleRequestDto {


        @NotBlank
        private String title; //제목

        @NotBlank
        private String content; //내용

        @NotBlank
        private CategoryType type; //카테고리



    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleResponseDto {

        private Long id;

        private Long username; //게시글 작성한 사용자 아이디

        private String name; //사용자 이름

        private String nickName;

        private String title; //제목

        private String content; //내용

        private CategoryType type; //카테고리


        private List<CommentDto.CommentResponseDto> comments;

        private LocalDateTime createAt;

        private LocalDateTime modifiedAt;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticlePatchDto {


        private String title; //제목

        private String content; //내용

        private CategoryType type; //카테고리

    }
}
