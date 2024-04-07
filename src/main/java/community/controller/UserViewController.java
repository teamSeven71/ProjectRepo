package community.controller;


import community.domain.user.UserEntity;
import community.dto.user.UserDto;
import community.exception.DuplicateEmailException;
import community.exception.DuplicateNicknameException;
import community.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class UserViewController {

    private final UserService userService;

    @Autowired
    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "/site/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/site/Join";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    // 유저 정보 페이지
    @GetMapping("/userInfo")
    public String UserInfo(Model model) {
        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = (UserEntity) userService.loadUserByUsername(username);

        model.addAttribute("user", user);

        return "/site/userPage";
    }

    //유저 닉네임 수정
    @PutMapping("/userInfo")
    public ResponseEntity<String> updateUser(@RequestBody UserDto.UserResponseDto userDto, @AuthenticationPrincipal UserEntity user) {
        String nickName = userDto.getNickName();
        String name = userDto.getName();
        String email = userDto.getEmail();

        // 닉네임과 이메일 중복 체크
        boolean isNickNameDuplicate = userService.existsByNickName(nickName);
        boolean isEmailDuplicate = userService.existsByEmail(email);

        if (isNickNameDuplicate || isEmailDuplicate) {
            if (isNickNameDuplicate && isEmailDuplicate) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATE_NICKNAME_AND_EMAIL");
            } else if (isNickNameDuplicate) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATE_NICKNAME");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATE_EMAIL");
            }
        }

        // 중복값이 없으면 사용자 정보 업데이트
        try {
            UserDto.UserResponseDto updatedUser = userService.updateUser(user.getId(), nickName, name, email);
            return ResponseEntity.ok("/site/userPage");
        } catch (DataIntegrityViolationException e) {
            // 데이터 무결성 위반 오류 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED_TO_UPDATE_USER");
        }
    }

    // 관리자 페이지
    @GetMapping("/admin")
    public String showMembers(@RequestParam(value = "search", required = false) String search,
                              @PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<UserDto.UserResponseDto> members;
        int startPage;
        int endPage;

        if (search != null && !search.isEmpty()) {
            members = userService.searchActiveMembers(search, pageable);
            startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
            endPage = Math.min(members.getPageable().getPageNumber() + 4, members.getTotalPages());
        } else {
            members = userService.getAllActiveMembers(pageable);
            startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
            endPage = Math.min(members.getPageable().getPageNumber() + 4, members.getTotalPages());
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("members", members);
        model.addAttribute("search", search);
        return "/site/adminPage";
    }


}
