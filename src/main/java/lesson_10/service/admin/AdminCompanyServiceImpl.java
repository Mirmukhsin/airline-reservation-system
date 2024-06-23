package lesson_10.service.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.CompanyDTO;
import lesson_10.entity.Company;
import lesson_10.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static lesson_10.mapper.CompanyMapper.COMPANY_MAPPER;

@Service
@RequiredArgsConstructor
public class AdminCompanyServiceImpl implements AdminCompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public List<Company> saveCompanies(@NonNull MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            List<Company> companies = new ObjectMapper().readValue(in, new TypeReference<>() {
            });
            return companyRepository.saveAll(companies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Company saveCompany(@NonNull CompanyDTO dto) {
        Company company = COMPANY_MAPPER.toEntity(dto);
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(@NonNull Integer id, @NonNull CompanyDTO dto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        company.setName(Objects.requireNonNullElse(dto.getName(), company.getName()));
        return companyRepository.save(company);
    }

    @Override
    public String deleteCompany(@NonNull Integer id) {
        if (companyRepository.findById(id).isPresent()) {
            companyRepository.deleteById(id);
            return "Company successfully deleted";
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public Company getCompany(@NonNull Integer id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
}
