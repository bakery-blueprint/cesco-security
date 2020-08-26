package com.github.bakery.cesco.week02.hotire;

import com.github.bakery.cesco.week02.Week02UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@EnableWebSecurity
public class Week02HotireSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        final DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsPasswordService(new InMemoryUserDetailsManager() {
            @Override
            public UserDetails updatePassword(UserDetails user, String newPassword) {
                return user;
            }
        });
        provider.setUserDetailsService(new Week02UserDetailsService());
        provider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        provider.afterPropertiesSet();
        return provider;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() throws Exception {
        return new AccessDeniedHandlerImpl() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                    AccessDeniedException accessDeniedException) {
                response.setStatus(4003);
            }
        };
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/week02/**")
            .authenticationProvider(authenticationProvider())
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
            .authorizeRequests()
            .mvcMatchers("/week02/second").permitAll()
            .mvcMatchers("/week02/user").hasAnyRole("USER")
            .mvcMatchers("/week02/accessDenied").hasRole("ACCESS_DENIED")
            .anyRequest()
            .authenticated()
            .expressionHandler(expressionHandler())
            .and()
            .csrf().disable();
    }
}
