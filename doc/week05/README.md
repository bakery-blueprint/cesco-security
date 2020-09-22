# 5주차 

## LogoutFilter 

기본전략으로 SimpleUrlLogoutSuccessHandler 사용한다. 

~~~java 
if (requiresLogout(request, response)) {
//....
}
~~~

해당 조건일 경우 logout 처리한다 

LogoutHandler 구현체로 CompositeLogoutHandler를 사용하는데

Composite은 Handler의 모음이다.

~~~java

http.logout(); 

// LogoutConfigurer를 확인할 수 있다. 

~~~
LogoutConfigurer.createLogoutFilter() 시점에 생성한다. 
LogoutConfigurer.addLogoutHandlers 할 때 마다 logoutHandlers에 추가하고 createLogoutFilter() 호출할 때 주입해준다.


### 과제 

- Custom LogoutHandler 를 만들어보자.

## UsernamePasswordAuthenticationFilter

user가 입력한 id / password를 통해 UsernamePasswordAuthenticationToken 생성하고 인증을 시도한다.

기본적으로 provider 중 Dao를 사용한다. 또한 Dao는 UserDetailsService를 사용한다.

