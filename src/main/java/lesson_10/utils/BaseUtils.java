package lesson_10.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public class BaseUtils {
    //    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Base64.Encoder encoder = Base64.getUrlEncoder();

    public String generateOTP(Integer userId) {
        return encoder.encodeToString((UUID.randomUUID().toString() + userId).getBytes());
    }
}