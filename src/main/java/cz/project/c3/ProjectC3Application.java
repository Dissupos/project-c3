package cz.project.c3;

import cz.project.c3.domain.user.*;
import cz.project.c3.repository.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

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
                           AddressRepository addressRepository, UniversityRepository universityRepository) {
        return (String... args) -> {
            log.debug("//---------------------------------Start init privileges");

            Privilege permStatistics = privilegeRepository.save(new Privilege("STATISTICS"));
            Privilege permUserRead = privilegeRepository.save(new Privilege("USER_READ"));
            Privilege permUserWrite = privilegeRepository.save(new Privilege("USER_WRITE"));

            log.debug("//---------------------------------Start init roles");
            Role adminRole = new Role("ADMINISTRATOR");
            adminRole.setPrivileges(Arrays.asList(permStatistics, permUserRead, permUserWrite));
            roleRepository.save(adminRole);

            Role userRole = new Role("STUDENT");
            userRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite));
            roleRepository.save(userRole);

            Role companyRole = new Role("COMPANY");
            companyRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite));
            roleRepository.save(companyRole);

            Role professorRole = new Role("PROFESSOR");
            professorRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite));
            roleRepository.save(professorRole);
            log.debug("//---------------------------------Start init users");
            Person adminPerson = personRepository.save(new Person("Test_name", "Test_lastname", SexType.MALE,
                    addressRepository.save(new Address("Czech Republic", "Brno"))));
            User admin = new PersonalUser("admin", new BCryptPasswordEncoder().encode("admin"), AccountType.ADMINISTRATOR,
                    "admin@admin.com", adminPerson);
            admin.setRoles(Arrays.asList(adminRole));
            userRepository.save(admin);
            Person userPerson = personRepository.save(new Person("user-name", "user-lastname", SexType.FEMALE,
                    addressRepository.save(new Address("Czech Republic", "Praha"))));
            University university = universityRepository.save(new University("VUT"));
            User user = new StudentUser("user", new BCryptPasswordEncoder().encode("user"), AccountType.STUDENT,
                    "user@user.com", userPerson, university);
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
        };

    }
}
