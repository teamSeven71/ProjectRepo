package community.mapper.user;

import community.domain.user.UserEntity;
import community.dto.user.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    /**
     * Entity -> Dto
     */
    UserDto.UserResponseDto toResponseDto(UserEntity userEntity);

    /**
     * Dto -> Entity
     */

    @Mapping(target="id", ignore = true)
    @Mapping(target="role", ignore = true)
    @Mapping(target = "articles", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserEntity toRequestEntity(UserDto.UserRequestDto userRequestDto);
}
