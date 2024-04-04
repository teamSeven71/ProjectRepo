package community.controller;

import community.constant.CategoryType;
import community.dto.user.ArticleCategoryDto;
import community.dto.user.ArticleDto;
import community.dto.user.CategoryDto;
import community.dto.user.CommentDto;
import community.service.ArticleCategoryService;
import community.service.ArticleService;
import community.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "아티클 Page")
@Controller
public class ArticlePageController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final ArticleCategoryService articleCategoryService;

    @Autowired
    public ArticlePageController(ArticleService articleService, CommentService commentService, ArticleCategoryService articleCategoryService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.articleCategoryService = articleCategoryService;
    }

    // 메인페이지 공지사항3개 조회
    @GetMapping("/")
    public String showNotice(Model model){
        long noticeId = 2;
        List<ArticleDto.ArticleResponseDto> notices = articleService.getAllArticlesByCategory(noticeId);
        // 공지사항 리스트를 역순으로 변경
        Collections.reverse(notices);
        model.addAttribute("notices", notices);
        return "/site/main";
    }



    @GetMapping("/articles/{categoryId}")
    public String showArticles(@PageableDefault(size=10) Pageable pageable, Model model, @PathVariable Long categoryId){
        Page<ArticleDto.ArticleResponseDto> articles = articleService.findAllArticleByCategory(categoryId, pageable);

        // 페이지 설정
        int startPage = Math.max(1, articles.getPageable().getPageNumber() - 4);
        int endPage = Math.min(articles.getPageable().getPageNumber() + 4, articles.getTotalPages());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("articles", articles);

        // 카테고리명 추가
        CategoryDto.CategoryResponseDto categoryDto = articleService.getAllCategoryName(categoryId);
        String categoryName = categoryDto.getCategoryName();

        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryId", categoryDto.getId());

        return "/site/articleList";
    }




    // 메인페이지의 공지사항 클릭시 & 게시글 목록 페이지에서 특정 게시글 클릭 시 : 해당 게시글 상세 페이지
    @GetMapping("/article/{id}")
    public String showDetails(
            Model model,
            @PathVariable Long id
    ) {
        //게시글 정보 조회
        ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
        model.addAttribute("article", article);

        // 카테고리 조회
        List<CategoryDto.CategoryResponseDto> categories = articleCategoryService.getCategoriesById(id);
        model.addAttribute("categories", categories);

        // 댓글 정보 조회
        List<CommentDto.CommentResponseDto> comments = commentService.readComment(id);
        model.addAttribute("comments", comments);

        // 댓글 개수 조회
        Long counts = commentService.countComment(id);
        model.addAttribute("counts", counts);

        return "/site/articleDetail";
    }

    // 게시글 작성 페이지 & 수정 페이지
    @GetMapping("/article/post")
    public String postArticle(Model model, @RequestParam(required = false) Long id, @RequestParam(required = false) Long categoryId)
    {
        if (id == null) {  // 등록
            model.addAttribute("article", new ArticleDto.ArticleResponseDto());
            model.addAttribute("categoryId",categoryId);
        } else {  // 수정
            ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
            model.addAttribute("article", article);
        }
        return "/site/writeArticle"; // writeArticle.html 파일을 응답으로 반환
    }
}
