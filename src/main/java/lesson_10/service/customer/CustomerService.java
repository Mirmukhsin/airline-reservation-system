package lesson_10.service.customer;

import lesson_10.entity.Airport;
import lesson_10.entity.City;
import lesson_10.entity.Flight;
import lesson_10.entity.Ticket;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CustomerService {
    List<Ticket> myTickets();
    List<City> getCities();

    List<Airport> getAirports(@NonNull String cityName);

    List<Flight> getFlights(@NonNull String airportName);

    Ticket buyTicket(@NonNull Integer id);

    Ticket ticketCancellation(@NonNull Integer id);
}
