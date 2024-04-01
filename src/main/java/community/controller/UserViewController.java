package community.controller;


import community.dto.user.UserDto;
import community.service.ArticleService;
import community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
    // 관리자 페이지
    @GetMapping("/admin")
    public String showMembers(Model model){
        List<UserDto.UserResponseDto> members = userService.getAllActiveMembers();
        model.addAttribute("members", members);
        return "/site/adminPage";
    }
}   
