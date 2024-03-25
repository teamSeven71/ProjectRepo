package community.service;

import community.domain.user.ArticleEntity;
import community.dto.user.ArticleDto;
import community.mapper.user.ArticleMapper;
import community.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ArticleDto.ArticleResponseDto> getAllArticles() {
        List<ArticleEntity> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ArticleDto.ArticleResponseDto getArticleById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        return articleMapper.toResponseDto(article);
    }

    // 다른 필요한 메서드들을 추가할 수 있습니다.
}
