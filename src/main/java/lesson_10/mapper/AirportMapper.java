package lesson_10.mapper;

import lesson_10.dto.AirportDTO;
import lesson_10.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirportMapper {
    AirportMapper AIRPORT_MAPPER = Mappers.getMapper(AirportMapper.class);

    Airport toEntity(AirportDTO airportDTO);
}
