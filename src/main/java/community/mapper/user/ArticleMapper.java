package community.mapper.user;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import community.dto.user.ArticleDto;
import community.dto.user.CommentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = Jsr310JpaConverters.LocalDateTimeConverter.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ArticleMapper {

    /**
     * Entity -> Dto
     */
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.nickName", target = "nickName")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "articleCategories", target = "categories", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    ArticleDto.ArticleResponseDto toResponseDto(ArticleEntity articleEntity);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.nickName", target = "nickName")
    @Mapping(source = "article.id", target = "articleId")
    CommentDto.CommentResponseDto toCommentResponseDto(CommentEntity commentEntity);

    default List<CommentDto.CommentResponseDto> mapCommentEntitiesToDto(List<CommentEntity> comments) {
        return comments.stream()
                .map(this::toCommentResponseDto)
                .collect(Collectors.toList());
    }



    /**
     * Dto -> Entity
     */

//    @Mapping(source = "userId", target = "user.id") //게시글 작성자 매핑
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "articleCategories", ignore = true)
    ArticleEntity toResponseEntity(ArticleDto.ArticleResponseDto articleResponseDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "goodCount", ignore = true)
    @Mapping(target = "badCount", ignore = true)
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "articleCategories", ignore = true)
    ArticleEntity toReqeustEntity(ArticleDto.ArticleRequestDto articleRequestDto, UserEntity userEntity);

}
