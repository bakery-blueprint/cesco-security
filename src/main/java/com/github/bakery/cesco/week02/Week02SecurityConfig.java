package com.github.bakery.cesco.week02;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * TODO : 2주차 과제 authenticationManager
 *  /week02/** 로 시작하는 path의 경우
 *  Custom AuthenticationProvider를 생성하여 Week02UserDetailsService의 유저를 생성하기
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebSecurity
public class Week02SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * TODO: 2주차 과제 Role이 ADMIN인 경우에도 USER 권한을 가질 수 있게 변경 해보자.
     * hint : RoleHierarchyVoter
     */

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        return new DaoAuthenticationProvider();
//    }

    /*
     * TODO: 2주차 과제 Custom AccessDeniedHandler를 추가하여, error response를 변경해보기.
     */

//    @Bean
//    AccessDeniedHandler accessDeniedHandler() {
//        return new AccessDeniedHandlerImpl();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/week02/**")
            // hint : AbstractUserDetailsAuthenticationProvider
//            .authenticationProvider()
            // hint : access denied handler
//            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
            .authorizeRequests()
            .antMatchers("/week02/user").hasAnyRole("USER")
            .mvcMatchers("/week02/accessDenied").hasRole("ACCESS_DENIED")
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }
}
