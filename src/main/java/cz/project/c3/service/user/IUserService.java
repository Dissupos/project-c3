package cz.project.c3.service.user;

import cz.project.c3.domain.dto.UserDTO;
import cz.project.c3.domain.dto.UserRegisterDTO;
import cz.project.c3.domain.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 */
public interface IUserService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('USER_READ')")
    Optional<User> getCurrentUser();

    @Transactional
    User register(UserRegisterDTO dto);

    @Transactional
    @PreAuthorize("hasRole('USER_WRITE')")
    User updateUser(UserDTO dto);

    @Transactional
    User save(User user);

    @Transactional(readOnly = true)
    boolean isUsernameExists(String username);

    @Transactional(readOnly = true)
    boolean isEmailExists(String email);

}
