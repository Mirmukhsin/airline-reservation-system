package lesson_10.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AirportDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String cityName;
}
