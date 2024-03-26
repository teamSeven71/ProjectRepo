package community.mapper.user;

import community.domain.user.ArticleEntity;
import community.domain.user.CommentEntity;
import community.domain.user.UserEntity;
import community.dto.user.CommentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;


@Mapper(
        componentModel = "spring",
        uses = Jsr310JpaConverters.LocalDateTimeConverter.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
@Component //
public interface CommentMapper {

    /**
     * Entity -> Dto
     */
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "user.nickName", target = "nickName")
    CommentDto.CommentResponseDto toResponseDto(CommentEntity commentEntity);

    /**
     * Dto -> Entity
     */

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "articleId", target = "article.id")
    CommentEntity toResponseEntity(CommentDto.CommentResponseDto commentResponseDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "article", source = "articleEntity")
    @Mapping(target = "content", source = "commentRequestDto.content")
    CommentEntity toRequestEntity(CommentDto.CommentRequestDto commentRequestDto, UserEntity userEntity, ArticleEntity articleEntity);
}
