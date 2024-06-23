package lesson_10.mapper;

import lesson_10.dto.CityDTO;
import lesson_10.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {
    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    City toEntity(CityDTO dto);
}
