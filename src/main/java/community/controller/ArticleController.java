package community.controller;

import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Tag(name = "아티클 CRUD")
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleDto.ArticleResponseDto addArticle(
            @RequestBody ArticleDto.ArticleRequestDto request
            ,@AuthenticationPrincipal UserEntity user
    ) {
        return articleService.save(request, user);
    }

    @GetMapping
    public List<ArticleDto.ArticleResponseDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleDto.ArticleResponseDto getArticle(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    // 다른 필요한 API 메서드들을 추가할 수 있습니다.
}
