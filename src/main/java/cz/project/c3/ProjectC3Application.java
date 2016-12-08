package cz.project.c3;

import cz.project.c3.domain.user.Privilege;
import cz.project.c3.domain.user.Role;
import cz.project.c3.domain.user.User;
import cz.project.c3.repository.user.PrivilegeRepository;
import cz.project.c3.repository.user.RoleRepository;
import cz.project.c3.repository.user.UserRepository;
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
    CommandLineRunner init(UserRepository userRepository,
                           RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        return (String... args) -> {


            log.debug("//---------------------------------Start init privileges");
            Privilege permStatistics = privilegeRepository.save(new Privilege("PERM_STATISTICS"));
            log.debug("//---------------------------------Start init roles");
            Role adminRole = new Role("ADMINISTRATOR");
            adminRole.setPrivileges(Arrays.asList(permStatistics));
            roleRepository.save(adminRole);
            log.debug("//---------------------------------Start init users");
            User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"), "admin@admin.com");
            admin.setRoles(Arrays.asList(adminRole));
            userRepository.save(admin);
            User user = new User("user", new BCryptPasswordEncoder().encode("user"), "user@user.com");
            userRepository.save(user);
        };

    }
}
