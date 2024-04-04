package community.controller;


import community.domain.user.UserEntity;
import community.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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

    // 관리자 페이지
    @GetMapping("/admin")
    public String showMembers(@PageableDefault(size=10) Pageable pageable, Model model) {
        Page<UserEntity> members = userService.getAllActiveMembers(pageable);
        int startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
        int endPage = Math.min(members.getPageable().getPageNumber()+4, members.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("members", members);
        return "/site/adminPage";
    }


}   
