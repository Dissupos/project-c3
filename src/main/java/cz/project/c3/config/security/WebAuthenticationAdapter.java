package cz.project.c3.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebAuthenticationAdapter extends GlobalAuthenticationConfigurerAdapter {
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    UserDetailsService getUserDetailService() {
        return (String username) ->
                new org.springframework.security.core.userdetails.User(username, "password",
                        AuthorityUtils.NO_AUTHORITIES);
    }
}
