package community.dto.user;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalTime;
import java.util.List;

public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserRequestDto {

        @NotBlank(message = "이메일을 작성해주세요.")
        private String username; //사용자 아이디

        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$",
                message = "비밀번호는 8~15자 영문, 숫자, 특수문자 조합이어야 합니다.")
        private String password;

        @NotBlank(message = "이름을 작성해주세요.")
        private String name; //사용자 이름


        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        private String nickName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserResponseDto {

        private Long id;

        private String nickName;

        private String name;

        private String password;

        private String username;

        private String role;

        private List<ArticleEntity> articles;

        private List<CommentEntity> comments;


    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class UserPatchDto {
//
//
////        @NotNull
////        private String nickName; //닉네임 수정
//
//        //패스워드 수정 (추후)
//
//    }
}
