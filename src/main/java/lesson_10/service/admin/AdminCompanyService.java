package lesson_10.service.admin;

import lesson_10.dto.CompanyDTO;
import lesson_10.entity.Company;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminCompanyService {
    List<Company> saveCompanies(@NonNull MultipartFile file);

    Company saveCompany(@NonNull CompanyDTO dto);

    Company updateCompany(@NonNull Integer id, @NonNull CompanyDTO dto);

    String deleteCompany(@NonNull Integer id);

    Company getCompany(@NonNull Integer id);

    List<Company> getCompanies();
}
