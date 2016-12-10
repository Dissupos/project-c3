package cz.project.c3.service.user.impl;

import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.other.University;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.person.Person;
import cz.project.c3.domain.role.Role;
import cz.project.c3.service.other.ICompanyService;
import cz.project.c3.service.other.IUniversityService;
import cz.project.c3.service.person.IAddressService;
import cz.project.c3.service.person.IPersonService;
import cz.project.c3.service.role.IRoleService;
import cz.project.c3.web.dto.UserDTO;
import cz.project.c3.web.dto.UserRegisterDTO;
import cz.project.c3.domain.user.*;
import cz.project.c3.repository.user.UserRepository;
import cz.project.c3.web.error.UserAlreadyExistException;
import cz.project.c3.web.error.UserNotFoundException;
import cz.project.c3.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;
    private final IRoleService roleService;
    private final IPersonService personService;
    private final IAddressService addressService;
    private final IUniversityService universityService;
    private final ICompanyService companyService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository repository, IRoleService roleService, IPersonService personService, IAddressService addressService, IUniversityService universityService, ICompanyService companyService) {
        this.repository = repository;
        this.roleService = roleService;
        this.personService = personService;
        this.addressService = addressService;
        this.universityService = universityService;
        this.companyService = companyService;
    }

    @Override
    public Optional<User> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.ofNullable(user);
    }

    @Override
    public User register(UserRegisterDTO dto) {
        if (isUsernameExists(dto.getUsername()) || isEmailExists(dto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address or login already exists!");
        }

        Address address = new Address(dto.getCountry(), dto.getCity());
        address = addressService.save(address);
        Person person = personService.save(new Person(dto.getFirstName(), dto.getLastName(), dto.getSex(), address));
        User user;
        switch (dto.getAccountType()) {
            case COMPANY:
                Company company = companyService.save(new Company(dto.getCompanyName()));
                user = new CompanyUser(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getAccountType(), dto.getEmail(), person,
                        company);
                break;
            case PROFESSOR:
                user = new ProfessorUser(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getAccountType(), dto.getEmail(),
                        person, getOrCreateUniversity(dto.getUniversityName()));
                break;
            case STUDENT:
                user = new StudentUser(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getAccountType(), dto.getEmail(),
                        person, getOrCreateUniversity(dto.getUniversityName()));
                break;
            default:
                user = new PersonalUser(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getAccountType(), dto.getEmail(),
                        person);
                break;
        }

        Optional<Role> role = roleService.getByName(getRoleNameByType(user.getType()));
        user.setRoles(Arrays.asList(role.get()));
        user = save(user);

        return user;
    }

    @Override
    public User updateUser(UserDTO dto) {
        Optional<User> userOptional = getCurrentUser();
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        PersonalUser user = (PersonalUser) userOptional.get();
        Person person = user.getPerson();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setSex(dto.getSex());
        personService.save(person);
        Address address = person.getAddress();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        addressService.save(address);

        switch (user.getType()) {
            case COMPANY:
                CompanyUser companyUser = (CompanyUser) user;
                Company company = companyUser.getCompany();
                company.setName(dto.getCompanyName());
                companyService.save(company);
                break;
            case PROFESSOR:
                ProfessorUser professorUser = (ProfessorUser) user;
                if (professorUser.getUniversity().getName().equals(dto.getUniversityName())) {
                    Optional<University> universityOptional = universityService.getByName(dto.getUniversityName());
                    if (universityOptional.isPresent()) {
                        professorUser.setUniversity(universityOptional.get());
                    }
                }
                user = professorUser;
                break;
            case STUDENT:
                StudentUser studentUser = (StudentUser) user;
                if (studentUser.getUniversity().getName().equals(dto.getUniversityName())) {
                    Optional<University> universityOptional = universityService.getByName(dto.getUniversityName());
                    if (universityOptional.isPresent()) {
                        studentUser.setUniversity(universityOptional.get());
                    }
                }
                user = studentUser;
                break;
        }

        return save(user);
    }

    private University getOrCreateUniversity(String name) {
        Optional<University> existUniversity = universityService.getByName(name);
        University university;
        if (existUniversity.isPresent()) {
            university = existUniversity.get();
        } else {
            university = universityService.save(new University(name));
        }
        return university;
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
