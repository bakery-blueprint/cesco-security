package com.github.bakery.cesco.week03;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.bakery.cesco.week01.Role;
import com.github.bakery.cesco.week04.HotireHeaderWriter;
import com.github.bakery.cesco.week04.LocalMapSecurityContextRepository;
import com.github.bakery.cesco.week05.CustomLoggingLogoutHandler;

//@Order(0)
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/", "/info", "/async/**").permitAll()
            .mvcMatchers(HttpMethod.POST, "/account").permitAll()
            .mvcMatchers(HttpMethod.GET, "/account").hasRole(Role.USER.name())
            .mvcMatchers("/admin").hasRole("ADMIN")
            .anyRequest()
            .authenticated();

        http.securityContext()
            .securityContextRepository(new LocalMapSecurityContextRepository());

        http.headers()
            .addHeaderWriter(new HotireHeaderWriter());

        http.logout().addLogoutHandler(new CustomLoggingLogoutHandler());
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
