package cz.project.c3.service.user;

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


}
