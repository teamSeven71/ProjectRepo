package community.mapper.user;

import community.domain.user.CategoryEntity;
import community.domain.user.CommentEntity;
import community.dto.user.CategoryDto;
import community.dto.user.CommentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CategoryMapper {

    /**
     * Entity -> Dto
     */
    CategoryDto.CategoryResponseDto toResponseDto(CategoryEntity categoryEntity);
}
