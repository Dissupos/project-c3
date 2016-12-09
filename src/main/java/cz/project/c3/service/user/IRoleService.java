package cz.project.c3.service.user;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.user.Role;

public interface IRoleService {
    @Transactional(readOnly = true)
    Optional<Role> getByName(String name);
}
