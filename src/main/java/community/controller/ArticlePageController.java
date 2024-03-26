package community.controller;

import community.constant.CategoryType;
import community.domain.user.ArticleEntity;
import community.dto.user.ArticleDto;
import community.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "아티클 Page")
@Controller
public class ArticlePageController {
    private final ArticleService articleService;

    @Autowired
    public ArticlePageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 메인페이지 공지사항3개 조회
    @GetMapping("/articles")
    public String showNotice(Model model){
        List<ArticleDto.ArticleResponseDto> notices = articleService.getAllArticlesByCategory(CategoryType.NOTICE);
        model.addAttribute("notices", notices);
        return "메인 페이지 url";
    }

    // 카테고리 클릭 시 해당 카테고리 게시글 목록 페이지
    @GetMapping("/articles/{type}")
    public String showArticles(Model model, @PathVariable CategoryType type){
        List<ArticleDto.ArticleResponseDto> articles = articleService.getAllArticlesByCategory(type);
        model.addAttribute("articlees", articles);
        return "게시글 목록 페이지 url";
    }
}
