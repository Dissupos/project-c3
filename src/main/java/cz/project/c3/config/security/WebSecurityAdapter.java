package cz.project.c3.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @since 0.0.1
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/health", "/api/info", "/api/metrics", "/api/mappings", "/api/trace").hasRole("STATISTICS")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/users/logged_user")
                .permitAll()
                //.successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .logout()
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(false)
                .and().csrf().disable();
    }

    //    private AuthenticationSuccessHandler loginSuccessHandler() {
    //        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) -> {
    //            httpServletResponse.getWriter().append(buildErrorAttributesAsJSON(200, null, "OK"));
    //            httpServletResponse.setStatus(200);
    //        };
    //    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) -> {
            httpServletResponse.getWriter().append(buildErrorAttributesAsJSON(200, null, "OK"));
            httpServletResponse.setStatus(200);
        };
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) -> {
            httpServletResponse.getWriter().append(buildErrorAttributesAsJSON(401, "Authentication failure", "Bad Credentials"));
            httpServletResponse.setStatus(401);
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) -> {
            httpServletResponse.getWriter().append(buildErrorAttributesAsJSON(403, "Access denied", "You don't have permissions"));
            httpServletResponse.setStatus(403);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) -> {
            httpServletResponse.getWriter().append(buildErrorAttributesAsJSON(401, "Not authenticated", "Log in please"));
            httpServletResponse.setStatus(401);
        };
    }

    private ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                return errorAttributes;
            }
        };
    }

    private String buildErrorAttributesAsJSON(Integer status, String error, String message) throws JsonProcessingException {
        Map<String, Object> ret = errorAttributes().getErrorAttributes(RequestContextHolder.getRequestAttributes(), false);
        ret.put("status", status);
        if (error != null) {
            ret.put("error", error);
        } else {
            ret.remove("error");
        }
        ret.put("message", message);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ret);
    }
}
