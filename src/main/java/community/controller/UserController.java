package community.controller;

import community.dto.user.AddUserRequest;
import community.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);  // 회원 가입(저장)
        return "redirect:/login";   // 회원 가입 처리 후 로그인 페이지로 강제 이동
    }

    @GetMapping("/userinfo")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 현재 사용자의 이름 가져오기

        model.addAttribute("username", username); // 모델에 사용자 이름 추가

        return "userinfo"; // userinfo.html 템플릿으로 이동
    }
}
