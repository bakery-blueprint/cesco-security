package com.github.bakery.cesco.week02;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

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

    DefaultWebSecurityExpressionHandler expressionHandler() {
        final DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        // 해당 값을 넣을 때 포맷이 정해져 있고 ROLE_ 가 필수
        // 일반 포맷 "high > low", 2개 넣을려면 "\n" 구분자로 split 한다.
//        roleHierarchy.setHierarchy();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

//    AuthenticationProvider authenticationProvider() {
//        return new DaoAuthenticationProvider();
//    }

    /*
     * TODO: 2주차 과제 Custom AccessDeniedHandler를 추가하여, error response를 변경해보기.
     */

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
//            .expressionHandler(expressionHandler())
            .and()
            .csrf().disable();
    }
}
