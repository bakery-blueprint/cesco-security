package com.github.bakery.cesco.week04;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(-12345)
@EnableWebSecurity
public class Week04WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/week04/**").authorizeRequests().anyRequest().authenticated().and()
            .securityContext().securityContextRepository(new LocalMapSecurityContextRepository()).and()
            .headers().addHeaderWriter(new Week04HeaderWriter());

    }
}
