package lesson_10.service.auth;

import lesson_10.entity.AuthUser;
import lesson_10.entity.AuthUserOTP;
import org.springframework.lang.NonNull;

public interface AuthUserOtpService {
    AuthUserOTP create(@NonNull AuthUserOTP authUserOTP);

    AuthUserOTP createOTP(@NonNull AuthUser authUser);
}
