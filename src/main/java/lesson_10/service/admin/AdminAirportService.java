package lesson_10.service.admin;

import lesson_10.dto.AirportDTO;
import lesson_10.entity.Airport;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminAirportService {
    List<Airport> saveAirports(@NonNull MultipartFile file);

    Airport saveAirport(@NonNull AirportDTO dto);

    Airport updateAirport(@NonNull Integer id, @NonNull AirportDTO dto);

    String deleteAirport(@NonNull Integer id);

    Airport getAirport(@NonNull Integer id);

    List<Airport> getAirports();

}
