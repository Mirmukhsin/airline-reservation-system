package lesson_10.service.agent;

import lesson_10.dto.FlightDTO;
import lesson_10.entity.Flight;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AgentService {
    List<Flight> registerFlights(@NonNull MultipartFile file);

    Flight registerFlight(@NonNull FlightDTO dto);

    Flight updateFlight(@NonNull Integer id, @NonNull FlightDTO dto);
}
