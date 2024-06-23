package lesson_10.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.entity.Airport;
import lesson_10.entity.City;
import lesson_10.entity.Flight;
import lesson_10.entity.Ticket;
import lesson_10.service.customer.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('CUSTOMER')")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @Operation(summary = "Get All My Tickets", description = "Get All My Tickets")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tickets found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ticket.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tickets Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/myTickets")
    public ResponseEntity<List<Ticket>> myTickets() {
        return ResponseEntity.ok(customerServiceImpl.myTickets());
    }

    @Operation(summary = "Get All Cities", description = "Get All Cities")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cities found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = City.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "City Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(customerServiceImpl.getCities());
    }

    @Operation(summary = "Get All Airports by City Name", description = "Get All Airports by City Name")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Airports found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Airport.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airport Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/airports/{cityName}")
    public ResponseEntity<List<Airport>> getAirports(@PathVariable String cityName) {
        return ResponseEntity.ok(customerServiceImpl.getAirports(cityName));
    }

    @Operation(summary = "Get All Flights by Airport Name", description = "Get All Flights by Airport Name")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Flights found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Flight.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Flights Not Found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NotFoundException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/flights/{airportName}")
    public ResponseEntity<List<Flight>> getFlights(@PathVariable String airportName) {
        return ResponseEntity.ok(customerServiceImpl.getFlights(airportName));
    }

    @Operation(summary = "Buy Ticket", description = "Buy Ticket")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully bought",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ticket.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/buyTicket/{id}")
    public ResponseEntity<Ticket> buyTicket(@PathVariable Integer id) {
        return ResponseEntity.ok(customerServiceImpl.buyTicket(id));
    }

    @Operation(summary = "Ticket Cancellation", description = "Ticket Cancellation")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ticket canceled successfully",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ticket.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }
            )
    })
    @GetMapping("/ticketCancellation/{id}")
    public ResponseEntity<Ticket> ticketCancellation(@PathVariable Integer id) {
        return ResponseEntity.ok(customerServiceImpl.ticketCancellation(id));
    }
}
