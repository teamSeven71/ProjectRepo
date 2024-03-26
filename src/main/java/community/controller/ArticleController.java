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

    // 게시물 등록 api
    @PostMapping
    public ResponseEntity<ArticleDto.ArticleResponseDto> addArticle(
            @RequestBody ArticleDto.ArticleRequestDto request,
            @AuthenticationPrincipal UserEntity user
    ) {
        ArticleDto.ArticleResponseDto responseDto = articleService.save(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 모든 category가 공지사항인 글 조회
    @GetMapping
    public ResponseEntity<List<ArticleDto.ArticleResponseDto>> getAllNotice() {
        List<ArticleDto.ArticleResponseDto> articles = articleService.getAllNotice();
        return ResponseEntity.ok(articles);
    }

    //게시물 단 건 조회 api
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> getArticle(@PathVariable Long id) {
        ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }


    //게시물 단 건 삭제 api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle( @PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        articleService.deleteById(id, user);
        return ResponseEntity.ok("Article deleted successfully.");
    }

    //게시물 단 건 수정 api
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody ArticleDto.ArticleRequestDto request,
            @AuthenticationPrincipal UserEntity user  //
    ) {
        ArticleDto.ArticleResponseDto updatedArticle = articleService.updateArticle(id, request, user);
        return ResponseEntity.ok(updatedArticle);
    }
    // 다른 필요한 API 메서드들을 추가할 수 있습니다.
}
