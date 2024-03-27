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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
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
    @GetMapping("/techhome")
    public String showNotice(Model model){
        List<ArticleDto.ArticleResponseDto> notices = articleService.getAllArticlesByCategory(CategoryType.NOTICE);
        model.addAttribute("notices", notices);
        return "/site/index";
    }

    // 카테고리 클릭 시 해당 카테고리 게시글 목록 페이지
    @GetMapping("/articles/{type}")
    public String showArticles(Model model, @PathVariable CategoryType type){
        List<ArticleDto.ArticleResponseDto> articles = articleService.getAllArticlesByCategory(type);
        //id 순으로 정렬.
        Collections.sort(articles, Comparator.comparing(ArticleDto.ArticleResponseDto::getId));
        model.addAttribute("articles", articles);
        return "/site/free";
    }


    // 메인페이지의 공지사항 클릭시 & 게시글 목록 페이지에서 특정 게시글 클릭 시 : 해당 게시글 상세 페이지
    @GetMapping("/article/{id}")
    public String showDetails(
            Model model,
            @PathVariable Long id
    ) {
        ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "/site/articleDetail";
    }

    @GetMapping("/article/post")
    public String postArticle(Model model, @RequestParam(required = false) Long id)
    {
        if (id == null) {  // 등록
            model.addAttribute("article", new ArticleDto.ArticleResponseDto());
        } else {  // 수정
            ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
            model.addAttribute("article", article);
        }
        return "/site/writeArticle"; // writeArticle.html 파일을 응답으로 반환
    }
}
