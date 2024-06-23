package lesson_10.service.admin;

import lesson_10.dto.AuthUserDTO;
import lesson_10.entity.AuthUser;
import lombok.NonNull;

import java.util.List;

public interface AdminAuthService {
    AuthUser changeUserStatus(@NonNull Integer id,boolean status);

    AuthUser createAgent(@NonNull AuthUserDTO dto);

    AuthUser updateUser(@NonNull Integer id,@NonNull AuthUserDTO dto);

    String deleteUser(@NonNull Integer id);

    AuthUser getUser(@NonNull Integer id);

    List<AuthUser> getAgents();

    List<AuthUser> getCustomers();
}
