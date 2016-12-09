package cz.project.c3;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cz.project.c3.domain.user.AccountType;
import cz.project.c3.domain.user.Address;
import cz.project.c3.domain.user.Person;
import cz.project.c3.domain.user.Privilege;
import cz.project.c3.domain.user.Role;
import cz.project.c3.domain.user.SexType;
import cz.project.c3.domain.user.User;
import cz.project.c3.repository.user.AddressRepository;
import cz.project.c3.repository.user.PersonRepository;
import cz.project.c3.repository.user.PrivilegeRepository;
import cz.project.c3.repository.user.RoleRepository;
import cz.project.c3.repository.user.UserRepository;

/**
 * Main class, we start application from here
 */
@Configuration
@SpringBootApplication
public class ProjectC3Application {

    // logger
    private static final Logger log = LoggerFactory.getLogger(ProjectC3Application.class);

    public static void main(String[] args) {
        SpringApplication.run(ProjectC3Application.class, args);
    }

    /**
     * This method started with application, here we add our users and foo data
     */
    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository, PersonRepository personRepository,
            AddressRepository addressRepository) {
        return (String... args) -> {
            log.debug("//---------------------------------Start init privileges");

            Privilege permStatistics = privilegeRepository.save(new Privilege("STATISTICS"));
            Privilege permUserRead = privilegeRepository.save(new Privilege("USER_READ"));

            log.debug("//---------------------------------Start init roles");
            Role adminRole = new Role("ADMINISTRATOR");
            adminRole.setPrivileges(Arrays.asList(permStatistics, permUserRead));
            roleRepository.save(adminRole);

            Role userRole = new Role("STUDENT");
            userRole.setPrivileges(Arrays.asList(permUserRead));
            roleRepository.save(userRole);
            log.debug("//---------------------------------Start init users");
            Person adminPerson = personRepository.save(new Person("Test_name", "Test_lastname", SexType.MALE,
                    addressRepository.save(new Address("Czech Republic", "Brno"))));
            User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"), AccountType.ADMINISTRATOR,
                    "admin@admin.com", adminPerson);
            admin.setRoles(Arrays.asList(adminRole));
            userRepository.save(admin);
            Person userPerson = personRepository.save(new Person("user-name", "user-lastname", SexType.FEMALE,
                    addressRepository.save(new Address("Czech Republic", "Praha"))));
            User user = new User("user", new BCryptPasswordEncoder().encode("user"), AccountType.STUDENT,
                    "user@user.com", userPerson);
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
        };

    }
}
