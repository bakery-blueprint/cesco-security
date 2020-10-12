package com.github.bakery.cesco.week06;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;

@EnableWebSecurity
public class Week06SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionFixation().changeSessionId().invalidSessionUrl("/").sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(new SessionRegistryImpl()).expiredUrl("/week05");
    }
}
