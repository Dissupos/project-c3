package cz.project.c3.service.user.impl;

import cz.project.c3.domain.user.User;
import cz.project.c3.repository.user.UserRepository;
import cz.project.c3.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return repository.findOneByUsername(username);
    }
}
