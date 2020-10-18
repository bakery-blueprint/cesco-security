# 7주차 

## 메서드 시큐리티 

MVC가 아닌 자바 어플리케이션에서 시큐리티 제어를 하고 싶을 때 사용 

JWT에서 사용 하면 좋다. 

~~~Java
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
~~~

- securedEnabled : @Secured

- jsr250Enabled : @RolesAllowed

- prePostEnabled: @PreAuthorize / @PostAuthorize 

: parameter 검사 및 returnObject 검사 등 추가적인 기능을 사용할 수 있다. 

roleHierarchy 구조를 이해하지 못하기 때문에 AccessDecisionManager Custom이 필요하다. (GlobalMethodSecurityConfiguration extends)

### 간단한 동작 원리 

GlobalMethodSecuritySelector 을 통해서 AutoConfig 한다.

jsr250Enabled의 경우 Jsr250MethodSecurityMetadataSource 처리 

나머지는 GlobalMethodSecurityConfiguration 친구가 처리한다.

만약 GlobalMethodSecurityConfiguration 을 extends 하면 

~~~java
boolean skipMethodSecurityConfiguration = GlobalMethodSecurityConfiguration.class
				.isAssignableFrom(importingClass);

if (!skipMethodSecurityConfiguration) {
			classNames.add(GlobalMethodSecurityConfiguration.class.getName());
}
~~~
: GlobalMethodSecuritySelector 에서 skipMethodSecurityConfiguration true가 나와서 GlobalMethodSecurityConfiguration을 selector에 넣지 않는다.


GlobalMethodSecurityConfiguration 내부에서는 우리가 설정한 값을 통해 MethodSecurityMetadataSource을 빈으로 생성한다.

추가적으로 MethodSecurityInterceptor을 빈으로 생성한다. (AOP처리에 사용)

GlobalMethodSecuritySelector 에서 MethodSecurityMetadataSourceAdvisorRegistrar도 생성한다.

MethodSecurityMetadataSourceAdvisorRegistrar 친구는 GlobalMethodSecurityConfiguration에서 생성한 MethodSecurityMetadataSource을 통해


MethodSecurityMetadataSourceAdvisor을 Bean으로 등록한다.







