package community.controller;


import community.domain.user.UserEntity;
import community.dto.user.UserDto;
import community.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
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
    public String UserInfo(Model model){
        // 현재 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = (UserEntity) userService.loadUserByUsername(username);

        model.addAttribute("user",user);

        return "/site/userPage";
    }

    //유저 닉네임 수정
    @PutMapping("/userInfo")
    public String updateUser(String nickName, @AuthenticationPrincipal UserEntity user, Model model) {


        UserDto.UserResponseDto updatedUser = userService.updateUser(user.getId(), nickName);
        model.addAttribute("user", updatedUser);

        return "/site/userPage";
    }

    // 관리자 페이지
    @GetMapping("/admin")
    public String showMembers(@PageableDefault(size=10) Pageable pageable, Model model) {
        Page<UserDto.UserResponseDto> members = userService.getAllActiveMembers(pageable);
        int startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
        int endPage = Math.min(members.getPageable().getPageNumber()+4, members.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("members", members);
        return "/site/adminPage";
    }


}   
