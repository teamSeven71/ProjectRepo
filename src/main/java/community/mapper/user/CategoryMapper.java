package community.mapper.user;

import community.domain.user.CommentEntity;
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

//    /**
//     * Entity -> Dto
//     */
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "article.id", target = "articleId")
//    @Mapping(source = "user.nickName", target = "nickName")
//    CommentDto.CommentResponseDto toResponseDto(CommentEntity commentEntity);
}
