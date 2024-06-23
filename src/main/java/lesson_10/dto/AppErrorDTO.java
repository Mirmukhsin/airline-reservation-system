package lesson_10.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AppErrorDTO {
    private final String friendlyMessage;
    private final Object developerMessage;
    private final String errorPath;
    private final Integer errorCode;

    public AppErrorDTO(String friendlyMessage, String errorPath, Integer errorCode) {
        this(friendlyMessage, friendlyMessage, errorPath, errorCode);
    }

    public AppErrorDTO(String friendlyMessage, Object developerMessage, String errorPath, Integer errorCode) {
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
        this.errorPath = errorPath;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return """
                \n
                *friendlyMessage : %s*
                *errorPath : %s*
                *errorCode : %s*
                *developerMessage* : `%s`
                """.formatted(friendlyMessage, errorPath, errorCode, developerMessage);
    }
}
