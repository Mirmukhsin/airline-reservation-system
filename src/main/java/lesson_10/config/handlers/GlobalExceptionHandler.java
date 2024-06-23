package lesson_10.config.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lesson_10.dto.AppErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    // Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<AppErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String friendlyMessage = "Not Valid Exception";
        String errorPath = request.getRequestURI();
        Map<String, List<String>> developerMessage = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            developerMessage.compute(field, (s, values) -> {
                if (!Objects.isNull(values)) {
                    values.add(message);
                } else {
                    values = new ArrayList<>(Collections.singleton(message));
                }
                return values;
            });
        }
        AppErrorDTO appErrorDTO = new AppErrorDTO(friendlyMessage, developerMessage, errorPath, 400);
        log.error("Validation Error : {}", appErrorDTO);
        return ResponseEntity.status(400).body(appErrorDTO);

    }

    // Internal server error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDTO> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String friendlyMessage = "Internal Server Error";
        String developerMessage = e.getMessage();
        String errorPath = request.getRequestURI();
        AppErrorDTO appErrorDto = new AppErrorDTO(friendlyMessage, developerMessage, errorPath, 500);
        log.error("Server Error : {}", appErrorDto);
        return ResponseEntity.status(500).body(appErrorDto);
    }
}
