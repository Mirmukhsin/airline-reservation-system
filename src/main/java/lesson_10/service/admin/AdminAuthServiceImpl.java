package lesson_10.service.admin;

import lesson_10.config.handlers.NotFoundException;
import lesson_10.dto.AuthUserDTO;
import lesson_10.entity.AuthUser;
import lesson_10.enums.Role;
import lesson_10.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static lesson_10.mapper.AuthUserMapper.AUTH_USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUser changeUserStatus(@NonNull Integer id, boolean status) {
        AuthUser authUser = authUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        authUser.setActive(status);
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser createAgent(@NonNull AuthUserDTO dto) {
        AuthUser agent = AUTH_USER_MAPPER.toEntity(dto);
        if (authUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email Already Taken");
        }
        if (authUserRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username Already Taken");
        }
        agent.setRole(Role.AGENT);
        agent.setActive(true);
        agent.setPassword(passwordEncoder.encode(dto.getPassword()));
        return authUserRepository.save(agent);
    }

    @Override
    public AuthUser updateUser(@NonNull Integer id, @NonNull AuthUserDTO dto) {
        AuthUser authUser = authUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        authUser.setEmail(Objects.requireNonNullElse(dto.getEmail(), authUser.getEmail()));
        authUser.setUsername(Objects.requireNonNullElse(dto.getUsername(), authUser.getUsername()));
        authUser.setPassword(Objects.requireNonNullElse(passwordEncoder.encode(dto.getPassword()), authUser.getPassword()));
        return authUserRepository.save(authUser);
    }

    @Override
    public String deleteUser(@NonNull Integer id) {
        if (authUserRepository.findById(id).isPresent()) {
            authUserRepository.deleteById(id);
            return "User successfully deleted!!!";
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public AuthUser getUser(@NonNull Integer id) {
        return authUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
    }

    @Override
    public List<AuthUser> getAgents() {
        return authUserRepository.findAllByRole(Role.AGENT);
    }

    @Override
    public List<AuthUser> getCustomers() {
        return authUserRepository.findAllByRole(Role.CUSTOMER);
    }
}
