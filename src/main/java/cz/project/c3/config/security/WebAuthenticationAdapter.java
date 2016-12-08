package cz.project.c3.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cz.project.c3.domain.user.User;
import cz.project.c3.repository.user.UserRepository;

@Configuration
public class WebAuthenticationAdapter extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    UserRepository userRepository;
    
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    UserDetailsService getUserDetailService() {
        return (String username) -> {
            Optional<User> user = userRepository.findOneByUsername(username);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UsernameNotFoundException("User not found");
        };
    }
}
