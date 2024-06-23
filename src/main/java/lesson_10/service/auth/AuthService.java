package lesson_10.service.auth;

import lesson_10.dto.AuthUserDTO;
import lesson_10.dto.GenerateTokenRequestDTO;
import org.springframework.lang.NonNull;

public interface AuthService {
    String register(@NonNull AuthUserDTO dto);

    String generateToken(@NonNull GenerateTokenRequestDTO requestDTO);

    String activateAccount(@NonNull String code);
}
