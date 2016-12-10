package cz.project.c3.service.role;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import cz.project.c3.domain.role.Role;

public interface IRoleService {
    @Transactional(readOnly = true)
    Optional<Role> getByName(String name);
}
