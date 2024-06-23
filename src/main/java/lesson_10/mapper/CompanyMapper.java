package lesson_10.mapper;

import lesson_10.dto.CompanyDTO;
import lesson_10.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    CompanyMapper COMPANY_MAPPER = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyDTO dto);
}
