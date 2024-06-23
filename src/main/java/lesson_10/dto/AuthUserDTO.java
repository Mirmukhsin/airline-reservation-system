package lesson_10.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
