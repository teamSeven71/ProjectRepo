package community.dto.admin;

import community.dto.user.CommentDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NoticeDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeRequestDto {


        @NotBlank
        private String title; //제목

        @NotBlank
        private String content; //내용



    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeResponseDto {

        private Long id;

        private Long username; //게시글 작성한 사용자 아이디

        private String name; //사용자 이름

        private String nickName;

        private String title; //제목

        private String content; //내용

        private LocalDate createAt;

        private LocalTime modifiedAt;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticePatchDto {


        private String title; //제목

        private String content; //내용

    }
}
