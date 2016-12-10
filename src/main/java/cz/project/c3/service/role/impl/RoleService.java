package cz.project.c3.service.role.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.project.c3.domain.role.Role;
import cz.project.c3.repository.role.RoleRepository;
import cz.project.c3.service.role.IRoleService;

@Service
public class RoleService implements IRoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> getByName(String name) {
        return repository.findOneByName(name);
    }

}
