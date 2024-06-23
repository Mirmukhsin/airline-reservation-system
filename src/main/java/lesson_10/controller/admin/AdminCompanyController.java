package lesson_10.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.CompanyDTO;
import lesson_10.entity.Company;
import lesson_10.service.admin.AdminCompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/company")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminCompanyController {
    private final AdminCompanyServiceImpl adminCompanyService;

    @Operation(summary = "Get Company", description = "Get Company by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Company found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Company.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Company Not Found",
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
    public ResponseEntity<Company> getCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(adminCompanyService.getCompany(id));
    }

    @Operation(summary = "Get All Companies", description = "Get All Companies")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Companies found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Company.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Companies Not Found",
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
    @GetMapping("/getCompanies")
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok(adminCompanyService.getCompanies());
    }

    @Operation(summary = "Create New Company", description = "Create new Company")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Company.class)
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
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CompanyDTO dto) {
        return ResponseEntity.ok(adminCompanyService.saveCompany(dto));
    }

    @Operation(summary = "Create New Companies from file", description = "Create New Companies from file")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Company.class)
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
    @PostMapping(value = "/saveCompanies", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Company>> saveCompanies(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(adminCompanyService.saveCompanies(file));
    }

    @Operation(summary = "Update Company", description = "Update Company")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Company.class)
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
                    description = "Company Not Found",
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
    public ResponseEntity<Company> updateCompany(@PathVariable Integer id, @RequestBody CompanyDTO dto) {
        return ResponseEntity.ok(adminCompanyService.updateCompany(id, dto));
    }

    @Operation(summary = "Delete Company", description = "Delete Company")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Company Not Found",
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
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(adminCompanyService.deleteCompany(id));
    }
}
