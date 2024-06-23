package lesson_10.service.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.CityDTO;
import lesson_10.entity.City;
import lesson_10.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static lesson_10.mapper.CityMapper.CITY_MAPPER;

@Service
@RequiredArgsConstructor
public class AdminCityServiceImpl implements AdminCityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> saveCities(@NonNull MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            List<City> cities = new ObjectMapper().readValue(in, new TypeReference<>() {
            });
            return cityRepository.saveAll(cities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City saveCity(@NonNull CityDTO dto) {
        City city = CITY_MAPPER.toEntity(dto);
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(@NonNull Integer id, @NonNull CityDTO dto) {
        City city = cityRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        city.setName(Objects.requireNonNullElse(dto.getName(), city.getName()));
        city.setCountryName(Objects.requireNonNullElse(dto.getCountryName(), city.getCountryName()));
        return cityRepository.save(city);
    }

    @Override
    public String deleteCity(@NonNull Integer id) {
        if (cityRepository.findById(id).isPresent()) {
            cityRepository.deleteById(id);
            return "City successfully deleted";
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public City getCity(@NonNull Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }
}
