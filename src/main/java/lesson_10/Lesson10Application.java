package lesson_10;

import lesson_10.entity.AuthUser;
import lesson_10.enums.Role;
import lesson_10.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Lesson10Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson10Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            AuthUser admin = AuthUser.builder()
                    .username("Admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("9779"))
                    .role(Role.ADMIN)
                    .active(true)
                    .build();
            authUserRepository.save(admin);
        };
    }
}
