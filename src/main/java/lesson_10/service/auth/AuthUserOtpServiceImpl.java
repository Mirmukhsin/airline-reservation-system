package lesson_10.service.auth;

import lesson_10.entity.AuthUser;
import lesson_10.entity.AuthUserOTP;
import lesson_10.repository.AuthUserOTPRepository;
import lesson_10.utils.BaseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthUserOtpServiceImpl implements AuthUserOtpService {
    private final AuthUserOTPRepository authUserOTPRepository;
    private final BaseUtils utils;

    @Override
    public AuthUserOTP create(@NonNull AuthUserOTP authUserOTP) {
        return authUserOTPRepository.save(authUserOTP);
    }

    @Override
    public AuthUserOTP createOTP(@NonNull AuthUser authUser) {
        AuthUserOTP authUserOTP = AuthUserOTP.builder()
                .code(utils.generateOTP(authUser.getId()))
                .userId(authUser.getId())
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        return create(authUserOTP);
    }
}
