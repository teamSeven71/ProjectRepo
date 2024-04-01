package community.controller;

import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@Tag(name = "아티클 CRUD")
@RestController
@RequestMapping("/api/articles")
@Slf4j
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

//    //article의 categoryId가져오기
//    @GetMapping("/{id}/categories")
//    public ResponseEntity<List<Long>> getCategoryIdsForArticle(@PathVariable Long id) {
//        List<Long> categoryIds = articleService.getCategoryIdsForArticle(id);
//        return new ResponseEntity<>(categoryIds, HttpStatus.OK);
//    }

    //게시물 단 건 조회 api
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> getArticle(@PathVariable Long id) {
        ArticleDto.ArticleResponseDto article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }


    //게시물 단 건 삭제 api
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle( @PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        articleService.deleteById(id, user);
        return  ResponseEntity.noContent().build();
    }

    //게시물 단 건 수정 api
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto.ArticleResponseDto> updateBoard(
            @PathVariable Long id,
            @RequestBody ArticleDto.ArticleRequestDto request,
            @AuthenticationPrincipal UserEntity user  //
    ) {
        log.info("title: " + request.getTitle());
        log.info("content: " + request.getContent());
        ArticleDto.ArticleResponseDto updatedArticle = articleService.updateArticle(id, request, user);
        log.info("new-title: " + updatedArticle.getTitle());
        log.info("new-content: " + updatedArticle.getContent());
        return ResponseEntity.ok(updatedArticle);
    }

    // 다른 필요한 API 메서드들을 추가 가능
    // 나중에 게시글 목록 페이지에서 다른 카테고리 누르면 category에 해당하는 모든 글 조회 가능
    /*@GetMapping
    public ResponseEntity<List<ArticleDto.ArticleResponseDto>> getAllArticlesByCategory(@PathVariable CategoryType type) {
        List<ArticleDto.ArticleResponseDto> articles = articleService.getAllArticlesByCategory(type);
        return ResponseEntity.ok(articles);
    }*/

}
