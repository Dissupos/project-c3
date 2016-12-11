package cz.project.c3;

import cz.project.c3.domain.offer.Category;
import cz.project.c3.domain.offer.Offer;
import cz.project.c3.domain.other.Company;
import cz.project.c3.domain.other.University;
import cz.project.c3.domain.person.Address;
import cz.project.c3.domain.person.Person;
import cz.project.c3.domain.person.SexType;
import cz.project.c3.domain.role.Privilege;
import cz.project.c3.domain.role.Role;
import cz.project.c3.domain.user.*;
import cz.project.c3.repository.offer.OfferRepository;
import cz.project.c3.repository.other.CompanyRepository;
import cz.project.c3.repository.other.UniversityRepository;
import cz.project.c3.repository.person.AddressRepository;
import cz.project.c3.repository.person.PersonRepository;
import cz.project.c3.repository.role.PrivilegeRepository;
import cz.project.c3.repository.role.RoleRepository;
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
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository,
                           PrivilegeRepository privilegeRepository, PersonRepository personRepository,
                           AddressRepository addressRepository, UniversityRepository universityRepository,
                           OfferRepository offerRepository, CompanyRepository companyRepository) {
        return (String... args) -> {
            log.debug("//---------------------------------Start init privileges");

            Privilege permStatistics = privilegeRepository.save(new Privilege("STATISTICS"));
            Privilege permUserRead = privilegeRepository.save(new Privilege("USER_READ"));
            Privilege permUserWrite = privilegeRepository.save(new Privilege("USER_WRITE"));
            Privilege permOfferWrite = privilegeRepository.save(new Privilege("OFFER_EDITOR"));
            Privilege permOfferUser = privilegeRepository.save(new Privilege("OFFER_USER"));

            log.debug("//---------------------------------Start init roles");
            Role adminRole = new Role("ADMINISTRATOR");
            adminRole.setPrivileges(Arrays.asList(permStatistics, permUserRead, permUserWrite));
            roleRepository.save(adminRole);

            Role userRole = new Role("STUDENT");
            userRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferUser));
            roleRepository.save(userRole);

            Role companyRole = new Role("COMPANY");
            companyRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferWrite));
            roleRepository.save(companyRole);

            Role professorRole = new Role("PROFESSOR");
            professorRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferUser));
            roleRepository.save(professorRole);
            log.debug("//---------------------------------Start init users");
            // admin
            Person adminPerson = personRepository.save(new Person("admin-name", "admin-lastname", SexType.MALE,
                    addressRepository.save(new Address("Czech Republic", "Brno"))));
            User admin = new PersonalUser("admin", new BCryptPasswordEncoder().encode("admin"), AccountType.ADMINISTRATOR,
                    "admin@admin.com", adminPerson);
            admin.setRoles(Arrays.asList(adminRole));
            userRepository.save(admin);
            // user
            Address praha = addressRepository.save(new Address("Czech Republic", "Praha"));
            Person userPerson = personRepository.save(new Person("user-name", "user-lastname", SexType.FEMALE, praha
            ));
            University university = universityRepository.save(new University("VUT"));
            User user = new StudentUser("user", new BCryptPasswordEncoder().encode("user"), AccountType.STUDENT,
                    "user@user.com", userPerson, university);
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
            // company
            Person companyPerson = personRepository.save(new Person("company-name", "company-lastname", SexType.FEMALE,
                    praha));
            Company company = companyRepository.save(new Company("VUT"));
            User companyUser = new CompanyUser("company", new BCryptPasswordEncoder().encode("company"), AccountType.COMPANY,
                    "company@company.com", companyPerson, company);
            companyUser.setRoles(Arrays.asList(companyRole));
            userRepository.save(companyUser);
            // professor
            Person professorPerson = personRepository.save(new Person("professor-name", "professor-lastname", SexType.MALE,
                    praha));
            User professorUser = new ProfessorUser("professor", new BCryptPasswordEncoder().encode("professor"), AccountType.PROFESSOR,
                    "professor@professor.com", professorPerson, university);
            professorUser.setRoles(Arrays.asList(professorRole));
            userRepository.save(professorUser);
            log.debug("//---------------------------------Start init another tables");
            offerRepository.save(new Offer("test", "bla-bla-bla", company, praha, Category.IT));
        };

    }
}
