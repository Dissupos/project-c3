package cz.project.c3.service.user.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cz.project.c3.domain.dto.UserRegisterDTO;
import cz.project.c3.domain.user.AccountType;
import cz.project.c3.domain.user.Address;
import cz.project.c3.domain.user.Person;
import cz.project.c3.domain.user.Role;
import cz.project.c3.domain.user.User;
import cz.project.c3.repository.user.UserRepository;
import cz.project.c3.resource.base.error.UserAlreadyExistException;
import cz.project.c3.service.user.IAddressService;
import cz.project.c3.service.user.IPersonService;
import cz.project.c3.service.user.IRoleService;
import cz.project.c3.service.user.IUserService;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;
    private final IRoleService roleService;
    private final IPersonService personService;
    private final IAddressService addressService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository repository, IRoleService roleService, IPersonService personService,
            IAddressService addressService) {
        super();
        this.repository = repository;
        this.roleService = roleService;
        this.personService = personService;
        this.addressService = addressService;
    }

    @Override
    public Optional<User> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(user);
    }

    @Override
    public User register(UserRegisterDTO dto) {
        if (isUsernameExists(dto.getUsername()) || isEmailExists(dto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress or login already exists!");
        }

        Address address = new Address(dto.getCountry(), dto.getCity());
        address = addressService.save(address);
        Person person = new Person(dto.getFirstName(), dto.getLastName(), dto.getSex(), address);
        person = personService.save(person);
        User user = new User(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getAccountType(), dto.getEmail(),
                person);
        Optional<Role> role = roleService.getByName(getRoleNameByType(user.getType()));
        user.setRoles(Arrays.asList(role.get()));
        user = save(user);

        return user;
    }

    private String getRoleNameByType(AccountType type) {
        switch (type) {
        case PROFESSOR:
            return "PROFESSOR";
        case COMPANY:
            return "COMPANY";
        default:
            return "STUDENT";
        }
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    public boolean isUsernameExists(String username) {
        return repository.findOneByUsername(username).isPresent();
    }

    public boolean isEmailExists(String email) {
        return repository.findOneByEmail(email).isPresent();
    }
}
