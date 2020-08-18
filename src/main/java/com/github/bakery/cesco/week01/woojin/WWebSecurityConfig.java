package com.github.bakery.cesco.week01.woojin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WWebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Autowired
    private WAccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/woojin", "/woojin/info").permitAll()
            .mvcMatchers("/woojin/admin").hasRole("ADMIN")
            .anyRequest()
            .authenticated();

        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
        http.userDetailsService(accountService);
    }

    /**
     * TODO 1주차 과제
     * ADMIN / USER Role 각각 InMemory 로 생성하자..!!
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("woojin").password("{noop}1234").roles("ADMIN");
    }

}
