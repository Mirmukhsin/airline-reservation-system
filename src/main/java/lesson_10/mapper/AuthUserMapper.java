package lesson_10.mapper;

import lesson_10.dto.AuthUserDTO;
import lesson_10.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {
    AuthUserMapper AUTH_USER_MAPPER = Mappers.getMapper(AuthUserMapper.class);

    AuthUser toEntity(AuthUserDTO authUserCreateDTO);
}
