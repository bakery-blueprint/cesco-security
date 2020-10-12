# 6주차 

## SessionManagementFilter
- Session 변조 방지 전략
    - migrateSession(서블릿 3.0- 컨테이너 사용시 기본값)
    - changeSession(서블릿 3.1+ 컨테이너 사용시 기본값)
    - none
    - newSession -> extractAttributes에서 continue를 통해서 copy를 하지 않음
- invalidSessionUrl
    - invalid session일시에 redirect할 url
- maximumSessions
    - 로그인후 생성 가능한 session의 갯수(중복 로그인 갯수)
- maxSessionsPreventsLogin
    - maxSession 이후 로그인을 가능하게 할지 말지에 대한 유무    
- sessionRegistry
- expireSession
- session create policy
    - IF_REQUIRED
    - ALWAYS
    - NEVER - session을 안쓰는것은 아니지만, 추가적으로 생성을 하지 않음
    - STATELESS
- https://kangwoojin.github.io/programing/spring-security-basic-session-management-filter/
- tomcat 공식홈페이지에서 서블릿 버전을 확인 가능

## ExceptionTranslationFilter
- 마지막 2번째에 등록되어 있으며 FilterSecurityInterceptor 전에 등록되어 있는 Filter
- FilterSecurityInterceptor에서 발생한 error를 try-catch하여 처리하는 필터
- AuthenticationException, AccessDeniedException에 대해서 handling 해 줌
- AuthenticationException -> AuthenticationEntryPoint를 통해서 인증이 가능할 수 있게 도와주는 역할
- AccessDeniedException -> AccessDeniedHandler를 통해서 403 에러 처리
```java
http.excpetionHandling().accessDeniedPage("/access-denied")
```

## FilterSecurityInterceptor
- AccessDecisionManager를 사용하여 인가를 처리하는 Filter
- matchers 종류
    - mvcMatchers : ant pattern을 지원
    - antMatchers : ant pattern 방식
    - regexMatchers : 정규 표현식 방식
- permitAll : 모두 허용
- hasRole : hasAuthority 하위 , "ROLE_"가 prefix로 붙어 있음
- hasAuthority : "ROLE_" + role을 붙여서 사용
- anonymous : 비인증 유저만 접근 가능
- authenticated : 인증된 유저만 접근 가능
- rememberMe : rememberMe 기능으로 인증된 유저만 접근 가능
- fullyAuthenticated : rememberMe로 인증된 유저에 대해서 다시 로그인을 요청
- denyAll : 아무것도 허용하지 않음
- hasIpAddress : 해당 ip address인 유저만 허용
- not : 뒤에 나오는 조건을 역으로 사용

## RememberMeAuthenticationFilter
- token 기반으로 인증할 수 있는 filter

- https://kangwoojin.github.io/programing/spring-security-basic-remember-me-authentication-filter/

## Custom Filter

- https://kangwoojin.github.io/programing/spring-security-basic-add-custom-filter/

## 타임리프 스프링 시큐리티 확장팩, sec 네임스페이스
- https://kangwoojin.github.io/programing/spring-security-basic-thymeleaf-security/

