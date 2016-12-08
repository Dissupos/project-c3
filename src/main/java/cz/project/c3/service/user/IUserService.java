package cz.project.c3.service.user;

import cz.project.c3.domain.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 */
public interface IUserService {
    String PERM_USER_READ = "PERM_USER_READ";

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole(" + PERM_USER_READ + ")")
    Optional<User> getByUsername(String username);

}
