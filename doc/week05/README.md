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

### 과제 

- Custom LogoutHandler 를 만들어보자.


