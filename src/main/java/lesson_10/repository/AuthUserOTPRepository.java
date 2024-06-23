package lesson_10.repository;

import lesson_10.entity.AuthUserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserOTPRepository extends JpaRepository<AuthUserOTP, Integer> {
    @Query("select a from AuthUserOTP a where upper(a.code) = upper(?1) and a.deleted = false")
    Optional<AuthUserOTP> findByCodeIgnoreCase(String code);
}