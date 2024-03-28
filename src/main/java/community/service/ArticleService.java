package community.service;

import community.constant.CategoryType;
import community.domain.user.ArticleEntity;
import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.exception.ArticleNotFoundException;
import community.exception.UnauthorizedException;
import community.mapper.user.ArticleMapper;
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

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleDto.ArticleResponseDto save(@RequestBody ArticleDto.ArticleRequestDto request
                                             ,@AuthenticationPrincipal UserEntity user
                                    ){
        ArticleEntity article = articleMapper.toReqeustEntity(request, user);
        ArticleEntity savedArticle = articleRepository.save(article);
        return articleMapper.toResponseDto(article);
    }

    public List<ArticleDto.ArticleResponseDto> getAllArticlesByCategory(CategoryType type) {
        List<ArticleEntity> articles = articleRepository.findAllCategory(type);
        return articles.stream()
                .map(articleMapper::toResponseDto)
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
        article.setType(request.getType());

        articleRepository.save(article);

        return articleMapper.toResponseDto(article);
    }

    // 다른 필요한 메서드들을 추가할 수 있습니다.
}
