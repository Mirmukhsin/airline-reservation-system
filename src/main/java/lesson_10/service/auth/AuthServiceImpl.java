package lesson_10.service.auth;

import lesson_10.config.security.JWTTokenUtil;
import lesson_10.dto.AuthUserDTO;
import lesson_10.dto.GenerateTokenRequestDTO;
import lesson_10.entity.AuthUser;
import lesson_10.entity.AuthUserOTP;
import lesson_10.enums.Role;
import lesson_10.repository.AuthUserOTPRepository;
import lesson_10.repository.AuthUserRepository;
import lesson_10.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static lesson_10.mapper.AuthUserMapper.AUTH_USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserOtpServiceImpl authUserOtpService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenUtil jwtTokenUtil;
    private final AuthUserOTPRepository authUserOTPRepository;

    @Override
    public String register(@NonNull AuthUserDTO dto) {
        AuthUser authUser = AUTH_USER_MAPPER.toEntity(dto);
        if (authUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email Already Taken");
        }
        if (authUserRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username Already Taken");
        }
        authUser.setRole(Role.CUSTOMER);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUserRepository.save(authUser);
        AuthUserOTP authUserOTP = authUserOtpService.createOTP(authUser);
        Map<String, String> model = new HashMap<>();
        model.put("to", authUser.getEmail());
        model.put("code", authUserOTP.getCode());
        mailService.sendActivationMail(model);
        return "Success";
    }

    @Override
    public String generateToken(@NonNull GenerateTokenRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = requestDTO.getPassword();
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(username);
    }

    @Override
    public String activateAccount(@NonNull String code) {
        AuthUserOTP authUserOTP = authUserOTPRepository.findByCodeIgnoreCase(code).orElseThrow(() -> new RuntimeException("Invalid Code"));
        if (authUserOTP.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code is expired");
        }
        Integer userId = authUserOTP.getUserId();
        authUserRepository.activateUser(userId);
        return "Account Successfully Activated";
    }
}
