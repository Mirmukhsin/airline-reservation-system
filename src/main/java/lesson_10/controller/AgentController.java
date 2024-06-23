package lesson_10.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.FlightDTO;
import lesson_10.entity.Flight;
import lesson_10.service.agent.AgentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/agent")
@PreAuthorize("hasRole('AGENT')")
@RequiredArgsConstructor
public class AgentController {
    private final AgentServiceImpl agentService;

    @Operation(summary = "Create New Flights from file", description = "Create New Flights from file")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Flight.class)
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
    @PostMapping(value = "/registerFlights", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Flight>> registerFlights(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(agentService.registerFlights(file));
    }

    @Operation(summary = "Create New Flight", description = "Create new Flight")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Flight.class)
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
    @PostMapping(value = "/registerFlight")
    public ResponseEntity<Flight> registerFlight(@Valid @RequestBody FlightDTO dto) {
        return ResponseEntity.ok(agentService.registerFlight(dto));
    }

    @Operation(summary = "Update Flight", description = "Update Flight")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Flight.class)
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
                    responseCode = "404",
                    description = "Flight Not Found",
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
    @PutMapping("/updateFlight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody FlightDTO dto) {
        return ResponseEntity.ok(agentService.updateFlight(id, dto));
    }
}
