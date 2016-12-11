package cz.project.c3.service.user;

import cz.project.c3.domain.user.User;
import cz.project.c3.web.dto.UserDTO;
import cz.project.c3.web.dto.UserRegisterDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface IUserService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('USER_READ')")
    User getCurrentUser();

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
