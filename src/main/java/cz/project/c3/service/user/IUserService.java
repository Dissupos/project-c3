package cz.project.c3.service.user;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.dto.UserRegisterDTO;
import cz.project.c3.domain.user.User;

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
    User save(User user);
    
    @Transactional(readOnly = true)
    boolean isUsernameExists(String username);
    
    @Transactional(readOnly = true)
    boolean isEmailExists(String email);

}
