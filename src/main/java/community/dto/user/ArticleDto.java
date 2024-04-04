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

//        @NotBlank
//        private CategoryType type; //카테고리

        //카테고리명 요청
        private List<Long> categories;

    }



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleResponseDto {

        private Long id;

        private String email; //게시글 작성한 사용자 이메일

        private String name; //사용자 이름

        private String nickName;

        private String title; //제목

        private String content; //내용

        private List<CommentDto.CommentResponseDto> comments;

        private LocalDateTime createAt;

        private LocalDateTime modifiedAt;

        private LocalDateTime deletedAt;

        private Long goodCount;

        private Long badCount;

        private Long viewCount;

        private String categoryName;

        private List<Long> categories;


    }



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticlePatchDto {


        private String title; //제목

        private String content; //내용

//        private CategoryType type; //카테고리

        //수정 -> type ? List형태 Category
        private List<Long> categories;

    }
}