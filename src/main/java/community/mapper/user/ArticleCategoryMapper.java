package community.mapper.user;

import community.domain.user.ArticleCategoryEntity;
import community.domain.user.CategoryEntity;
import community.dto.user.ArticleCategoryDto;
import community.dto.user.CategoryDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ArticleCategoryMapper {

    /**
     * Entity -> Dto
     */
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "article.id", target = "articleId")
    ArticleCategoryDto.ArticleCategoryResponseDto toResponseDto(ArticleCategoryEntity articleCategoryEntity);
}
