package lesson_10.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.AuthUserDTO;
import lesson_10.entity.AuthUser;
import lesson_10.service.admin.AdminAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/auth")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminAuthServiceImpl adminAuthService;

    @Operation(summary = "Get User by id", description = "Get User by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found",
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
    @GetMapping("/getUser/{id}")
    public ResponseEntity<AuthUser> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(adminAuthService.getUser(id));
    }

    @Operation(summary = "Change User status", description = "Change User status")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully changed",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
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
                    description = "User Not Found",
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
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<AuthUser> changeStatus(@PathVariable Integer id,@RequestBody boolean status) {
        return ResponseEntity.ok(adminAuthService.changeUserStatus(id, status));
    }

    @Operation(summary = "Create New Agent", description = "Create New Agent")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Created",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
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
    @PostMapping("/createAgent")
    public ResponseEntity<AuthUser> createAgent(@Valid @RequestBody AuthUserDTO dto) {
        return ResponseEntity.ok(adminAuthService.createAgent(dto));
    }

    @Operation(summary = "Update User", description = "Update User Details")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Successfully Updated",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
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
                    description = "User Not Found",
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
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<AuthUser> updateUser(@PathVariable Integer id,@Valid @RequestBody AuthUserDTO dto) {
        return ResponseEntity.ok(adminAuthService.updateUser(id, dto));
    }

    @Operation(summary = "Delete User", description = "Delete User by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Successfully Deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User Not Found",
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
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(adminAuthService.deleteUser(id));
    }

    @Operation(summary = "Get All Agents", description = "Get All Agents")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Agents found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agents Not Found",
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
    @GetMapping("/getAgents")
    public ResponseEntity<List<AuthUser>> getAgents() {
        return ResponseEntity.ok(adminAuthService.getAgents());
    }

    @Operation(summary = "Get All Consumers", description = "Get All Consumers")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consumers found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthUser.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Consumers Not Found",
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
    @GetMapping("/getCustomers")
    public ResponseEntity<List<AuthUser>> getCustomers() {
        return ResponseEntity.ok(adminAuthService.getCustomers());
    }

}
