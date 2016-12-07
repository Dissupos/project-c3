package cz.project.c3.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @since 0.0.1
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .inMemoryAuthentication()
          .withUser("admin").password("admin").roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/login","/logout").permitAll()
            .anyRequest().permitAll()
        .and().formLogin()
            .defaultSuccessUrl("/", true)
            .failureHandler(new SimpleUrlAuthenticationFailureHandler())
            .permitAll()
                .and().exceptionHandling()
                    .accessDeniedPage("/403")
        .and().httpBasic()
            .disable()
        .logout()
            .logoutUrl("/logout")
//            .logoutSuccessHandler(null)
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(false)
//            .and().exceptionHandling()
//                .accessDeniedHandler(null)
        .and().csrf().disable();
    }
}
