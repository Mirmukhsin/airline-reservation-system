package lesson_10.service.customer;

import lesson_10.config.handlers.NotFoundException;
import lesson_10.config.security.SessionUser;
import lesson_10.entity.Airport;
import lesson_10.entity.City;
import lesson_10.entity.Flight;
import lesson_10.entity.Ticket;
import lesson_10.repository.AirportRepository;
import lesson_10.repository.CityRepository;
import lesson_10.repository.FlightRepository;
import lesson_10.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final SessionUser sessionUser;

    @Override
    public List<Ticket> myTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public List<Airport> getAirports(@NonNull String cityName) {
        return airportRepository.findAllByCityName(cityName);
    }

    @Override
    public List<Flight> getFlights(@NonNull String airportName) {
        return flightRepository.findAllByAirportName(airportName);
    }

    @Override
    public Ticket buyTicket(@NonNull Integer id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        if (flight.getTicketsNum() != 0) {
            Ticket ticket = Ticket.builder()
                    .departureFrom(flight.getDepartureFrom())
                    .destination(flight.getDestination())
                    .ownerName(sessionUser.getUser().getUsername())
                    .price(flight.getPrice())
                    .airportName(flight.getAirportName())
                    .departureTime(flight.getDepartureTime())
                    .landingTime(flight.getLandingTime())
                    .active(true)
                    .build();
            flightRepository.updateTicketsNum(id);
            return ticketRepository.save(ticket);
        } else {
            throw new RuntimeException("There are no tickets left in this direction!");
        }
    }

    @Override
    public Ticket ticketCancellation(@NonNull Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        ticket.setActive(!ticket.isActive());
        return ticketRepository.save(ticket);
    }
}
