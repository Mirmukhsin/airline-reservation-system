package lesson_10.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import lesson_10.dto.AppErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper mapper;
    private final JWTTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/**")
                        .permitAll()
                        .anyRequest()
                        .fullyAuthenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .addFilterBefore(new JWTTokenFilter(jwtTokenUtil, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(corsConfigurationSource())
                )
                .build();
    }

    // Access Denied Handler
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            accessDeniedException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = accessDeniedException.getMessage();
            int errorCode = 403;
            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
            response.setStatus(errorCode);
            ServletOutputStream outputStream = response.getOutputStream();
            mapper.writeValue(outputStream, appErrorDTO);
        };
    }

    // Authentication Entry Point
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, accessDeniedException) -> {
            accessDeniedException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = accessDeniedException.getMessage();
            int errorCode = 401;
            AppErrorDTO appErrorDTO = new AppErrorDTO(errorMessage, errorPath, errorCode);
            response.setStatus(errorCode);
            ServletOutputStream outputStream = response.getOutputStream();
            mapper.writeValue(outputStream, appErrorDTO);
        };
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    // Cors Configuration Source
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}