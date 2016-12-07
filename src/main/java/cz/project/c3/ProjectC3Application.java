package cz.project.c3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    CommandLineRunner init() {
        return (String... args) -> {
            log.debug("Start init application");
            // TODO
        };

    }
}
