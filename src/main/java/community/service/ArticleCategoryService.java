package community.service;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.ArticleEntity;
import community.domain.user.CategoryEntity;
import community.dto.user.ArticleDto;
import community.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleCategoryService {

    private final ArticleCategoryRepository articleCategoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public ArticleCategoryService(ArticleCategoryRepository articleCategoryRepository, CategoryService categoryService) {
        this.articleCategoryRepository = articleCategoryRepository;
        this.categoryService = categoryService;
    }

    public ArticleCategoryEntity save(ArticleEntity articleEntity, Long categoryId) {
        Optional<CategoryEntity> category= categoryService.getCategoryById(categoryId);

        ArticleCategoryEntity articleCategoryEntity = ArticleCategoryEntity.builder()
                .article(articleEntity)
                .category(category.orElse(null)) // 카테고리가 없으면 null로 설정
                .build();

        return articleCategoryRepository.save(articleCategoryEntity);
    }
}
