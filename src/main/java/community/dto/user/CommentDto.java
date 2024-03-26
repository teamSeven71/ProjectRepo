package community.dto.user;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CommentDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentRequestDto {

        private Long articleId; //댓글이 달린 게시글 dto

        private String content; //내용



    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentResponseDto {

        private Long id;

        private Long userId; //댓글 작성한 사용자 아이디

        private String nickName;

//        private Long likeNum; //좋아요 개수 -> 후순위

        private Long articleId; //댓글이 달린 게시글 id

        private String content; //내용

        private LocalDateTime createAt;

        private LocalDateTime modifiedAt;

    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class CommentPatchDto {
//
//
//        private String content; //내용
//
//    }

}
