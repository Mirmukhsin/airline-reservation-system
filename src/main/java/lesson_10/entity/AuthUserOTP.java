package lesson_10.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AuthUserOTP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer userId;

    @Future
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private boolean deleted;
}
