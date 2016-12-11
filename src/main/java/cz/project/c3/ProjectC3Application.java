package cz.project.c3;

import cz.project.c3.domain.meetup.Meetup;
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
import cz.project.c3.repository.meetup.MeetupRepository;
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
import java.util.Date;

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
                           OfferRepository offerRepository, CompanyRepository companyRepository, MeetupRepository meetupRepository) {
        return (String... args) -> {
            log.debug("//---------------------------------Start init privileges");
            if (userRepository.count() > 0) {
                return;
            }
            Privilege permStatistics = privilegeRepository.save(new Privilege("STATISTICS"));
            Privilege permUserRead = privilegeRepository.save(new Privilege("USER_READ"));
            Privilege permUserWrite = privilegeRepository.save(new Privilege("USER_WRITE"));
            Privilege permOfferWrite = privilegeRepository.save(new Privilege("OFFER_EDITOR"));
            Privilege permOfferUser = privilegeRepository.save(new Privilege("OFFER_USER"));
            Privilege permMeetupEditor = privilegeRepository.save(new Privilege("MEETUP_EDITOR"));
            Privilege permMeetupUser = privilegeRepository.save(new Privilege("MEETUP_USER"));

            log.debug("//---------------------------------Start init roles");
            Role adminRole = new Role("ADMINISTRATOR");
            adminRole.setPrivileges(Arrays.asList(permStatistics, permUserRead, permUserWrite, permMeetupEditor, permMeetupUser));
            roleRepository.save(adminRole);

            Role userRole = new Role("STUDENT");
            userRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferUser, permMeetupEditor, permMeetupUser));
            roleRepository.save(userRole);

            Role companyRole = new Role("COMPANY");
            companyRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferWrite, permMeetupEditor, permMeetupUser));
            roleRepository.save(companyRole);

            Role professorRole = new Role("PROFESSOR");
            professorRole.setPrivileges(Arrays.asList(permUserRead, permUserWrite, permOfferUser, permMeetupEditor, permMeetupUser));
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
            log.debug("//---------------------------------Start init offers");
            offerRepository.save(new Offer("Test Offer #1", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #2", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #3", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #4", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #5", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #6", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #7", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #8", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #9", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            offerRepository.save(new Offer("Test Offer #10", "What is Lorem Ipsum?\n" +
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n" +
                    "Why do we use it?\n" +
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                    "\n" +
                    "\n" +
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                    "\n" +
                    "Where can I get some?\n" +
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.", company, praha, Category.IT));
            log.debug("//---------------------------------Start init meetups");
            meetupRepository.save(new Meetup("SQL NYC, The NoSQL & NewSQL Database Meetup", "The NYC NoSQL & NewSQL Group \n" +
                    "(formerly known as The NYC MySQL Group) \n" +
                    "is the world’s largest database Meetup!\n" +
                    "\n" +
                    "Official URL: www.NYCSQL.com\n" +
                    "\n" +
                    "Learn amazing new data tech from technology creators, founders & tech execs CEO / CTO / CIO / VP.\n" +
                    "\n" +
                    "All of our presentations are geared for a general-audience of people interested in database technologies; many events go into deep-tech but always include prerequisite knowledge so that all attendees (beginners to advanced) can expect to be able to grasp the concepts and leave much more knowledgeable.\n" +
                    "\n" +
                    "Database Month Festival \n" +
                    "To remain cutting-edge, we host the biannual Database Month festival, focusing on:\n" +
                    "\n" +
                    "MySQL & SQL\n" +
                    "\n" +
                    "NoSQL including MongoDB, Couchbase, Aerospike, CouchDB, Redis, Riak, Cloudant\n" +
                    "\n" +
                    "NewSQL such as NuoDB, VoltDB, MemSQL\n" +
                    "\n" +
                    "MySQL forks & alternatives like Clustrix, MariaDB, Percona, Drizzle\n" +
                    "\n" +
                    "Big data technologies like Hadoop, Cassandra, MarkLogic, HPCC \n" +
                    "\n" +
                    "HD-Video of our previous events is regularly published to the Database Month website (http://www.DBMonth.com)\n" +
                    "\n" +
                    "Did you know that Techie Youth is the ONE-AND-ONLY organization providing career-opportunities to severely-at-risk foster-youth in New York?\n" +
                    "\n" +
                    "Techie Youth is a 501c3 not-for-profit charity of NYC that provides technology-education, followed by assistance beginning an IT-career, to at-risk youth in NYC & Long Island who are in foster-care and classified as being severely-at-risk of becoming homeless and/or incarcerated in the next 18-24 months.", (PersonalUser) professorUser, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Learn Python", "Learn Python NYC is open to developers of any background and skill level. Newcomers to programming, Python, or NYC are all welcome, as well as experienced programmers and locals. The purpose of this group is to promote learning and community involvement within the NYC Python community.", (PersonalUser) professorUser, praha, new Date(), Category.IT));

            meetupRepository.save(new Meetup("Women Who Code", "Mission:\n" +
                    "\n" +
                    "Women Who Code is a global nonprofit organization dedicated to inspiring women to excel in technology careers by creating a global, connected community of women in technology. The organization tripled in 2013 and has grown to be one of the largest communities of women engineers in the world.\n" +
                    "\n" +
                    "Empowerment:\n" +
                    "\n" +
                    "Women Who code is a professional community for women in tech. We provide an avenue for women to pursue a career in technology, help them gain new skills and hone existing skills for professional advancement, and foster environments where networking and mentorship are valued.\n" +
                    "\n" +
                    "Key Initiatives:\n" +
                    "\n" +
                    "Free technical study groups, Events featuring influential tech industry experts and investors, Hack events, Career and leadership development. Current and aspiring coders are welcome. \n" +
                    "\n" +
                    "Bring your laptop and a friend! \n" +
                    "\n" +
                    "Support Women Who Code:\n" +
                    "\n" +
                    "Click to volunteer, speak, or host an event.\n" +
                    "\n" +
                    "Donating to Women Who Code, Inc. (#46-4218859) directly impacts our ability to efficiently run this growing organization, helps us produce new programs that will increase our reach, and enables us to expand into new cities around the world ensuring that women and girls everywhere have the opportunity to pursue a career in technology.\n" +
                    "\n" +
                    "Women Who Code (WWCode) is dedicated to providing an empowering experience for everyone who participates in or supports our community, regardless of gender, gender identity and expression, sexual orientation, ability, physical appearance, body size, race, ethnicity, age, religion, or socioeconomic status. Because we value the safety and security of our members and strive to have an inclusive community, we do not tolerate harassment of members or event participants in any form. Our Code of Conduct applies to all events run by Women Who Code, Inc. If you would like to report an incident or contact our leadership team, please submit an incident report form.", (PersonalUser) professorUser, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Food+Tech Meetup", "The Food+Tech Meetup is the leading monthly event series for food innovators using technology and new business models to create a better food future. Our monthly events bring entrepreneurs, executives, investors, chefs, farmers, producers, journalists, hackers, designers and more together to network, share best practices and explore the future of food. The Food+Tech Meetup is organized by Food+Tech Connect in partnership with Holley Atkinson.\n" +
                    "\n" +
                    "The Food+Tech Meetup was founded in 2010 by Elizabeth McVay Greene, Holley Atkinson and Danielle Gould.", (PersonalUser) professorUser, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Beginner Programmers", "We’re a group of professional enthusiasts teaching other enthusiasts the power of code.  \n" +
                    "\n" +
                    "This group is not just for novice programmers, we also cater to anyone looking to understand and learn to program a new language from the ground up. We hold interactive classes, lectures, and study groups frequently with different trained instructors sharing their knowledge and experience. ", (PersonalUser) user, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Full Stack Engineering Meetup", "This Meetup group will feature speakers from across the stack: topics will include DevOps, Programming (front-end & back-end), Data Modeling, Collaboration, and Scaling. And of course, we'll ask each speaker \"what's in your stack?\"\n" +
                    "\n" +
                    "Why this group?\n" +
                    "\n" +
                    "Because great software engineers aren't \"Java guys\" or \"HTML5 ninjas,\" we're problem solvers. We take an idea and build it end to end: from just above the bare-metal, through the OS, db, server, network, and browser, and all the way up to the user. We collaborate, code, configure, and deploy. Full-stack engineers are a new breed of developer. We're more versatile than specialized, and we deserve a community that understands that.", (PersonalUser) user, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Future of Data", "This meetup is focused on the Future of Data and the open community data projects governed by the Apache Software Foundation. Geared towards developers, data scientists and ALL Data enthusiasts who are building modern data applications. Our meetups cover all data -- data-in-motion and data-at-rest.  Meetups provide an opportunity to listen, share and work hands on with other technologists in the open source and open community Apache tools.", (PersonalUser) professorUser, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Spark", "This is a meetup for NYC users of Spark (www.spark-project.org), the high-speed Scala-based cluster programming framework. We'll be rotating among locations in New York City. We'll also discuss other Spark-related projects, including the Hive-on-Spark port (Shark), and other new programming tools for big data. The meetup will include introductions to the various Spark features, case studies from current users, best practices for deployment and tuning, and future development plans.", (PersonalUser) professorUser, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("TechFam - A Tech Group for Blacks/Latinos/Diversity", "TechFam is a group formed to organize technologist and thrust innovation within the technology community from around the world. \n" +
                    "\n" +
                    "Join the Facebook group here: https://www.facebook.com/groups/48714836146...\n" +
                    "\n" +
                    "If you are are involved in digital media, a blogger, strategist, on-line/off-line designer, social networking, mobile technology, digital entertainment, database marketing, on-line/off-line marketing, public relations, webmaster, project management, networker or simply have a passion for all things technology, this is an opportunity to discuss, organize and activate your passion.\n" +
                    "\n" +
                    "Last the group is open to anyone interested in learning more about technology and how it impacts the present and future community.", (PersonalUser) admin, praha, new Date(), Category.IT));
            meetupRepository.save(new Meetup("Algorithmic Trading", "NYC Algorithmic Trading is for anyone interested in creating and using algorithms in the financial markets. We arrange monthly talks from practicing quants, algorithmic traders, trading technology experts, and academics. Our focus is practical, rather than theoretical. We enjoy talking about how to automate the purchase and sale of securities using statistics, machine learning, data mining, and algorithms.", (PersonalUser) companyUser, praha, new Date(), Category.IT));
        };

    }
}
