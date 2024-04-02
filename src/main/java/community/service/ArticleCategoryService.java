package community.service;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.ArticleEntity;
import community.domain.user.CategoryEntity;
import community.dto.user.ArticleCategoryDto;
import community.dto.user.ArticleDto;
import community.dto.user.CategoryDto;
import community.mapper.user.CategoryMapper;
import community.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleCategoryService {

    private final ArticleCategoryRepository articleCategoryRepository;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ArticleCategoryService(ArticleCategoryRepository articleCategoryRepository, CategoryService categoryService, CategoryMapper categoryMapper) {
        this.articleCategoryRepository = articleCategoryRepository;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public ArticleCategoryEntity save(ArticleEntity articleEntity, Long categoryId) {
        Optional<CategoryEntity> category= categoryService.getCategoryById(categoryId);

        ArticleCategoryEntity articleCategoryEntity = ArticleCategoryEntity.builder()
                .article(articleEntity)
                .category(category.orElse(null)) // 카테고리가 없으면 null로 설정
                .build();

        return articleCategoryRepository.save(articleCategoryEntity);
    }

    public List<CategoryDto.CategoryResponseDto> getCategoriesById(Long articleId){
        List<ArticleCategoryEntity> articleCategoryEntities = articleCategoryRepository.getCategoriesById(articleId);

        return articleCategoryEntities.stream()
                .map(entity -> CategoryDto.CategoryResponseDto.builder()
                        .id(entity.getCategory().getId())
                        .categoryName(entity.getCategory().getCategoryName())
                        // 필요에 따라 다른 속성들 추가
                        .build())
                .collect(Collectors.toList());
    }
}
