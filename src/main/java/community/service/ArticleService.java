package community.service;

import community.domain.user.*;
import community.dto.user.ArticleDto;
import community.dto.user.CategoryDto;
import community.exception.ArticleNotFoundException;
import community.mapper.user.ArticleMapper;
import community.mapper.user.CategoryMapper;
import community.repository.ArticleCategoryRepository;
import community.repository.ArticleRepository;
import community.repository.CategoryRepository;
import community.repository.CommentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private ArticleCategoryService articleCategoryService;
    private ArticleCategoryRepository articleCategoryRepository;
    private CommentRepository commentRepository;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper, ArticleCategoryService articleCategoryService, ArticleCategoryRepository articleCategoryRepository,
                          CommentRepository commentRepository,
                          CategoryRepository categoryRepository,
                          CategoryMapper categoryMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.articleCategoryService = articleCategoryService;
        this.articleCategoryRepository = articleCategoryRepository;
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public ArticleDto.ArticleResponseDto save(ArticleDto.ArticleRequestDto request, UserEntity user){

        //article entity 생성 > id성 됨
        ArticleEntity article = articleMapper.toReqeustEntity(request, user);

        article.setViewCount(0L);
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

    public CategoryDto.CategoryResponseDto getAllCategoryName(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        return categoryMapper.toResponseDto(categoryEntity);
    }


    //게시글 목록 pagination 때문에 필요
    public Page<ArticleDto.ArticleResponseDto> findAllArticleByCategory(Long categoryId, Pageable pageable) {
        Page<ArticleCategoryEntity> articleCategories = articleCategoryRepository.findAllArticleByCategory(categoryId, pageable);

        // ArticleCategoryEntity를 ArticleDto.ArticleResponseDto로 매핑하여 새로운 페이지를 생성합니다.
        List<ArticleDto.ArticleResponseDto> articles = articleCategories.getContent().stream()
                .map(articleCategory -> articleMapper.toResponseDto(articleCategory.getArticle()))
                .collect(Collectors.toList());

        return new PageImpl<>(articles, pageable, articleCategories.getTotalElements());
    }

    //그냥 공지사항 3개 메인페이지 조회시필요
    public List<ArticleDto.ArticleResponseDto> getAllArticlesByCategory(Long categoryId) {
        List<ArticleCategoryEntity> articleCategories = articleCategoryRepository.getAllArticleByCategory(categoryId);

        return articleCategories.stream()
                .map(articleCategory -> articleMapper.toResponseDto(articleCategory.getArticle()))
                .collect(Collectors.toList());
    }


    public ArticleDto.ArticleResponseDto getArticleById(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        return articleMapper.toResponseDto(article);
    }

    @Transactional
    public boolean deleteById(Long id, UserEntity user) {
        // 게시물 ID로 게시물을 조회합니다.
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        // 게시물이 존재하지 않는 경우 예외를 던집니다.
        if (optionalArticle.isEmpty()) {
            throw new ArticleNotFoundException("Article not found with id: " + id);
        }

        ArticleEntity article = optionalArticle.get();

        // 삭제 권한이 있는 경우 게시글과 관련된 댓글 먼저 삭제
        commentRepository.deleteCommentsByArticleId(id);
        // 삭제 권한이 있는 경우 게시글과 관련된 ArticleCategory data 먼저 삭제
        articleCategoryRepository.deleteArticleInCategory(id);

        // 게시물을 삭제합니다.
        articleRepository.deleteById(id);

        // 삭제가 성공적으로 이루어졌는지 확인
        return !articleRepository.existsById(id);
    }



    @Transactional
    public ArticleDto.ArticleResponseDto updateArticle(Long id, ArticleDto.ArticleRequestDto request, UserEntity user) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isEmpty()) {
            throw new ArticleNotFoundException("Article not found with id: " + id);
        }

        ArticleEntity article = optionalArticle.get();

        List<Long> categories = request.getCategories();

        // 게시글 내용 업데이트
        article.setContent(request.getContent());
        article.setTitle(request.getTitle());

        // 기존에 연결된 ArticleCategoryEntity를 모두 삭제합니다.
        articleCategoryRepository.deleteArticleInCategory(id);

        // 새로운 categories 목록으로 ArticleCategoryEntity를 생성하여 업데이트합니다.
        for (Long categoryId : categories) {
            // 저장 로직 - articleCategoryService를 사용하여 저장한다고 가정
            articleCategoryService.save(article, categoryId);
        }

        // articleRepository를 사용하여 게시글을 업데이트합니다.
        articleRepository.save(article);

        // 위와 같이 ResponseDto를 구성한 후 반환합니다.
        return articleMapper.toResponseDto(article);
    }

    @Transactional
    public Long getViewCount(Long articleId){
        ArticleEntity articleEntity = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + articleId));

        Long currentViewCount = articleEntity.getViewCount();
        articleEntity.setViewCount(currentViewCount + 1);
        return currentViewCount;
    }


    @Transactional
    public void updateViews(Long articleId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ArticleRepository를 사용하여 articleId를 통해 ArticleEntity 가져오기
        ArticleEntity articleEntity = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + articleId));

        // 이하 로직은 이전과 동일하게 수행
        Cookie[] cookies = request.getCookies();
        Map<String, String> mapCookie = new HashMap<>();

        if (request.getCookies() != null) {
            for (int i = 0; i < cookies.length; i++) {
                mapCookie.put(cookies[i].getName(), cookies[i].getValue());
            }

            String viewsCookie = mapCookie.get("views");
            String newCookie = "|" + articleId;

            // 쿠키가 없을 경우 쿠키 생성 후 조회수 증가
            if (viewsCookie == null || !viewsCookie.contains(newCookie)) {
                Cookie cookie = new Cookie("views", viewsCookie + newCookie);
                response.addCookie(cookie);

                articleEntity.setViewCount(articleEntity.getViewCount() + 1);
                articleRepository.save(articleEntity);
            }
        }
    }


}
