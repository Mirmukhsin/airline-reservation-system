package lesson_10.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.CityDTO;
import lesson_10.entity.City;
import lesson_10.service.admin.AdminCityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/city")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminCityController {
    private final AdminCityServiceImpl adminCityService;

    @Operation(summary = "Get City", description = "Get City by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "City found",
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
    @GetMapping("/get/{id}")
    public ResponseEntity<City> getCity(@PathVariable Integer id) {
        return ResponseEntity.ok(adminCityService.getCity(id));
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
    @GetMapping("/getCities")
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(adminCityService.getCities());
    }

    @Operation(summary = "Create New City", description = "Create new City")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = City.class)
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
    public ResponseEntity<City> createCity(@Valid @RequestBody CityDTO dto) {
        return ResponseEntity.ok(adminCityService.saveCity(dto));
    }

    @Operation(summary = "Create New Cities from file", description = "Create New Cities from file")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = City.class)
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
    @PostMapping(value = "/saveCities", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<City>> saveCities(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(adminCityService.saveCities(file));
    }

    @Operation(summary = "Update City", description = "Update City")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = City.class)
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
    @PutMapping("/update/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody CityDTO dto) {
        return ResponseEntity.ok(adminCityService.updateCity(id, dto));
    }

    @Operation(summary = "Delete City", description = "Delete City")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted"
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id) {
        return ResponseEntity.ok(adminCityService.deleteCity(id));
    }
}
