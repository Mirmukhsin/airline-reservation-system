package lesson_10.mapper;

import lesson_10.dto.FlightDTO;
import lesson_10.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FlightMapper {
    FlightMapper FLIGHT_MAPPER = Mappers.getMapper(FlightMapper.class);

    Flight toEntity(FlightDTO dto);
}
