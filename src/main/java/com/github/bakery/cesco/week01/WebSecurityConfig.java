package com.github.bakery.cesco.week01;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/", "/info").permitAll()
            .mvcMatchers(HttpMethod.POST, "/account").permitAll()
            .mvcMatchers(HttpMethod.GET, "/account").hasRole(Role.USER.name())
            .mvcMatchers("/admin").hasRole("ADMIN")
            .anyRequest()
            .authenticated();

        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
