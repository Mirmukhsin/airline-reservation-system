package lesson_10.service.admin;

import lesson_10.dto.CityDTO;
import lesson_10.entity.City;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminCityService {
    List<City> saveCities(@NonNull MultipartFile file);

    City saveCity(@NonNull CityDTO dto);

    City updateCity(@NonNull Integer id, @NonNull CityDTO dto);

    String deleteCity(@NonNull Integer id);

    City getCity(@NonNull Integer id);

    List<City> getCities();

}
