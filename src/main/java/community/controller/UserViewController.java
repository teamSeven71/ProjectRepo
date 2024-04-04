package community.controller;


import community.domain.user.UserEntity;
import community.dto.user.UserDto;
import community.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
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
//    @GetMapping("/userInfo")
//    public String UserInfo(Model model){
//        // 현재 로그인한 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
//
//        UserDto.UserResponseDto userDto = new UserDto.UserResponseDto();
//        userDto.setUsername()
//        return null;
//    }

    // 관리자 페이지
    @GetMapping("/admin")
    public String showMembers(Model model){
        List<UserDto.UserResponseDto> members = userService.getAllActiveMembers();
        model.addAttribute("members", members);
        return "/site/adminPage";
    }
}   
