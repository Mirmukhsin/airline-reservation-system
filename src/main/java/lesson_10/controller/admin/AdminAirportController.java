package lesson_10.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.AirportDTO;
import lesson_10.entity.Airport;
import lesson_10.service.admin.AdminAirportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/airport")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminAirportController {
    private final AdminAirportServiceImpl adminAirportService;

    @Operation(summary = "Get Airport", description = "Get Airport by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Airport found",
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
    @GetMapping("/get/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Integer id) {
        return ResponseEntity.ok(adminAirportService.getAirport(id));
    }

    @Operation(summary = "Get All Airports", description = "Get All Airports")
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
                    description = "Airports Not Found",
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
    @GetMapping("/getAirports")
    public ResponseEntity<List<Airport>> getAirports() {
        return ResponseEntity.ok(adminAirportService.getAirports());
    }

    @Operation(summary = "Create New Airport", description = "Create New Airport")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Airport.class)
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
    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@Valid @RequestBody AirportDTO dto) {
        return ResponseEntity.ok(adminAirportService.saveAirport(dto));
    }

    @Operation(summary = "Create New Airports from file", description = "Create New Airports from file")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Airport.class)
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
    @PostMapping(value = "/saveAirports", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Airport>> saveAirports(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(adminAirportService.saveAirports(file));
    }

    @Operation(summary = "Update Airport", description = "Update Airport")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Airport.class)
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Integer id, @RequestBody AirportDTO dto) {
        return ResponseEntity.ok(adminAirportService.updateAirport(id, dto));
    }

    @Operation(summary = "Delete Airport", description = "Delete Airport")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted"
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Integer id) {
        return ResponseEntity.ok(adminAirportService.deleteAirport(id));
    }
}
