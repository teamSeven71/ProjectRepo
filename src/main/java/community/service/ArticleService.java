package community.service;

import community.constant.CategoryType;
import community.domain.user.ArticleCategoryEntity;
import community.domain.user.ArticleEntity;
import community.domain.user.UserEntity;
import community.dto.user.ArticleCategoryDto;
import community.dto.user.ArticleDto;
import community.exception.ArticleNotFoundException;
import community.exception.UnauthorizedException;
import community.mapper.user.ArticleMapper;
import community.repository.ArticleCategoryRepository;
import community.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private ArticleCategoryService articleCategoryService;
    private ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper, ArticleCategoryService articleCategoryService, ArticleCategoryRepository articleCategoryRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.articleCategoryService = articleCategoryService;
        this.articleCategoryRepository = articleCategoryRepository;
    }

    public ArticleDto.ArticleResponseDto save(ArticleDto.ArticleRequestDto request, UserEntity user){

        //article entity 생성 > id성 됨
        ArticleEntity article = articleMapper.toReqeustEntity(request, user);
        ArticleEntity savedArticle = articleRepository.save(article);
/*
        //article id 얻어옴.
        Long articleId = article.getId();*/

        //client에서 받아온 categories를 따로 저장
        List<Long> categories = request.getCategories();

        // for문 돌리면서 articleCategoryDto에 articleId와 해당 카테고리 Id 하나씩 넣어줌
        for (Long categoryId : categories) {

            // 저장 로직 - articleCategoryService를 사용하여 저장한다고 가정
            articleCategoryService.save(savedArticle, categoryId);
        }

        return articleMapper.toResponseDto(savedArticle);
    }

    public List<ArticleDto.ArticleResponseDto> getAllArticlesByCategory(Long categoryId) {
        List<ArticleCategoryEntity> articleCategories = articleCategoryRepository.findAllArticleByCategory(categoryId);

        return articleCategories.stream()
                .map(articleCategory -> articleMapper.toResponseDto(articleCategory.getArticle()))
                .collect(Collectors.toList());
    }

    public ArticleDto.ArticleResponseDto getArticleById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        return articleMapper.toResponseDto(article);
    }

    public void deleteById(Long id, UserEntity user) {
        // 게시물 ID로 게시물을 조회합니다.
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        // 게시물이 존재하지 않는 경우 예외를 던집니다.
        if (optionalArticle.isEmpty()) {
            throw new ArticleNotFoundException("Article not found with id: " + id);
        }

        ArticleEntity article = optionalArticle.get();

        // 게시물을 작성한 사용자와 현재 로그인한 사용자가 같은지 확인합니다.
        if (!article.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this article.");
        }

        // 삭제 권한이 있는 경우 게시물을 삭제합니다.
        articleRepository.deleteById(id);
    }

    public ArticleDto.ArticleResponseDto updateArticle(Long id, ArticleDto.ArticleRequestDto request, UserEntity user) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        // 게시물이 존재하지 않는 경우 예외를 던집니다.
        if (optionalArticle.isEmpty()) {
            throw new ArticleNotFoundException("Article not found with id: " + id);
        }

        ArticleEntity article = optionalArticle.get();

        // 게시물을 작성한 사용자와 현재 로그인한 사용자가 같은지 확인합니다.
        if (!article.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to update this article.");
        }

        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
//        article.setType(request.getType());

        articleRepository.save(article);

        return articleMapper.toResponseDto(article);
    }

    // 다른 필요한 메서드들을 추가할 수 있습니다.
}
