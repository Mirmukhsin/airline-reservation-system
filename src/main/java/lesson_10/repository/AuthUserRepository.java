package lesson_10.repository;

import lesson_10.entity.AuthUser;
import lesson_10.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    List<AuthUser> findAllByRole(Role role);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.active = true where a.id = ?1 and a.deleted = false")
    void activateUser(Integer id);

    @Query("select a from AuthUser a where upper(a.email) = upper(?1) and a.deleted =  false ")
    Optional<AuthUser> findByEmail(String email);

    @Query("select a from AuthUser a where upper(a.username) = upper(?1) and a.deleted = false")
    Optional<AuthUser> findByUsername(String username);
}