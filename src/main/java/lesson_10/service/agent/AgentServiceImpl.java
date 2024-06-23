package lesson_10.service.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.FlightDTO;
import lesson_10.entity.Flight;
import lesson_10.repository.FlightRepository;
import lesson_10.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static lesson_10.mapper.FlightMapper.FLIGHT_MAPPER;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final FlightRepository flightRepository;
    private final MailService mailService;

    @Override
    public List<Flight> registerFlights(@NonNull MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            InputStream in = file.getInputStream();
            List<Flight> flights = objectMapper.readValue(in, new TypeReference<>() {
            });
            mailService.sendNewFlights(Map.of("lines", flights));
            return flightRepository.saveAll(flights);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flight registerFlight(@NonNull FlightDTO dto) {
        Flight flight = FLIGHT_MAPPER.toEntity(dto);
        mailService.sendNewFlights(Map.of("lines", List.of(flight)));
        return flightRepository.save(flight);
    }


    @Override
    public Flight updateFlight(@NonNull Integer id, @NonNull FlightDTO dto) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        flight.setPrice(dto.getPrice() == 0 ? flight.getPrice() : dto.getPrice());
        flight.setDepartureTime(Objects.requireNonNullElse(dto.getDepartureTime(), flight.getDepartureTime()));
        flight.setLandingTime(Objects.requireNonNullElse(dto.getLandingTime(), flight.getLandingTime()));
        flight.setTicketsNum(dto.getTicketsNum() == 0 ? flight.getTicketsNum() : dto.getTicketsNum());
        mailService.sendMail(Map.of("flight", flight));
        return flightRepository.save(flight);
    }
}
