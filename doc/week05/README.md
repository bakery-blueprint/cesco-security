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

## DefaultLoginPageGeneratingFilter (+ logout)

기본 login / logout page를 만들어주는 filter이다. custom한걸 추가하면 사라진다.

## BasicAuthenticationFilter

Http Basic 인증을 지원하는 Filter이다. 

요청 헤더에 Authorization: Basic dkfjdklfjdlfjldjl

username:password 를 base64로 인코딩하는 방식이다.

보안에 취약하기 때문에 Https 사용 권장한다.

마찬가지로 UsernamePasswordAuthenticationToken를 사용해서 한다.

차이점은 BasicAuthenticationFilter는 statusless하다.

## RequestCacheAwareFilter

현재 요청과 관련이 있는 캐시된 요청이 있는지 찾아서 적용하는 필터이다.

RequestCache 구현체로 HttpSessionRequestCache를 사용한다. 
 