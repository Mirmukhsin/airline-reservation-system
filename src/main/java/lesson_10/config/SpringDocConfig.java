package lesson_10.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PDP Online Java",
                version = "21",
                contact = @Contact(
                        name = "Mengdovulov Mirmuhsin",
                        email = "mirmuhsin@gmail.com",
                        url = "https://github.com/Mirmukhsin"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                ),
                termsOfService = "https://swagger.io/terms/",
                description = "This Document Designed For 10th Lesson"
        ),
        externalDocs = @ExternalDocumentation(
                description = "SpringDoc version 2",
                url = "https://springdoc.org/v/2"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Test Server"
                ),
                @Server(
                        url = "http://localhost:9090",
                        description = "Prod Server"
                )
        },
        security = @SecurityRequirement(
                name = "Bearer Authentication"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {
    @Bean
    public GroupedOpenApi authOpenApi() {
        return GroupedOpenApi.builder()
                .group("Auth")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminOpenApi() {
        return GroupedOpenApi.builder()
                .group("Admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi agentOpenApi() {
        return GroupedOpenApi.builder()
                .group("Agent")
                .pathsToMatch("/agent/**")
                .build();
    }

    @Bean
    public GroupedOpenApi customerOpenApi() {
        return GroupedOpenApi.builder()
                .group("Customer")
                .pathsToMatch("/customer/**")
                .build();
    }
}
