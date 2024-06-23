package lesson_10.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lesson_10.dto.AuthUserDTO;
import lesson_10.dto.GenerateTokenRequestDTO;
import lesson_10.service.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @Operation(summary = "User Registration", description = "User Registration")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "The user has been successfully registered",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_HTML_VALUE,
                                    schema = @Schema(implementation = String.class)
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
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthUserDTO dto) {
        return ResponseEntity.status(201).body(authService.register(dto));
    }

    @Operation(summary = "Get authentication Token", description = "Get authentication Token")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully generated token",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_HTML_VALUE,
                                    schema = @Schema(implementation = String.class)
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
    @PostMapping("/getToken")
    public ResponseEntity<String> getToken(@Valid @RequestBody GenerateTokenRequestDTO requestDTO) {
        return ResponseEntity.ok(authService.generateToken(requestDTO));
    }

    @Operation(summary = "Activate User Account", description = "Activate User Account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account Successfully Activated",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_HTML_VALUE,
                                    schema = @Schema(implementation = String.class)
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
    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activate(@PathVariable String code) {
        return ResponseEntity.ok(authService.activateAccount(code));
    }
}
