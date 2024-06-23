package lesson_10.service.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.AirportDTO;
import lesson_10.entity.Airport;
import lesson_10.repository.AirportRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static lesson_10.mapper.AirportMapper.AIRPORT_MAPPER;

@Service
@RequiredArgsConstructor
public class AdminAirportServiceImpl implements AdminAirportService {
    private final AirportRepository airportRepository;

    @Override
    public List<Airport> saveAirports(@NonNull MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            List<Airport> airports = new ObjectMapper().readValue(in, new TypeReference<>() {
            });
            return airportRepository.saveAll(airports);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airport saveAirport(@NonNull AirportDTO dto) {
        Airport airport = AIRPORT_MAPPER.toEntity(dto);
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(@NonNull Integer id, @NonNull AirportDTO dto) {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        airport.setName(Objects.requireNonNullElse(dto.getName(), airport.getName()));
        airport.setCityName(Objects.requireNonNullElse(dto.getCityName(), airport.getCityName()));
        return airportRepository.save(airport);
    }

    @Override
    public String deleteAirport(@NonNull Integer id) {
        if (airportRepository.findById(id).isPresent()) {
            airportRepository.deleteById(id);
            return "Airport successfully deleted";
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public Airport getAirport(@NonNull Integer id) {
        return airportRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    @Override
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }
}
