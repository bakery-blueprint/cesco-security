# 7주차 

## 메서드 시큐리티 

MVC가 아닌 자바 어플리케이션에서 시큐리티 제어를 하고 싶을 때 사용 

JWT에서 사용 하면 좋다. 

~~~Java
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
~~~

- securedEnabled : @Secured

- jsr250Enabled : @RolesAllowed

- prePostEnabled: 