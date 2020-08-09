# 1주차 

### 폼 인증 

Principal 을 통해 Security User 정보를 가져올 수 있다. 

ServletRequestMethodArgumentResolver을 통해 주입해준다.

Principal은 Spring Security class가 아닌 java.security에 존재하는 클래스이다. 


### User Auto Config

UserDetailsServiceAutoConfiguration 에 의해서 자동설정으로 기본 User을 생성해준다.

SecurityProperties 클래스를 보면 

name : "user" 을 사용하고, password : UUID.randomUUID().toString();

을 통해 생성해준다. 

ConfigurationProperties 이기 때문에 전부 yml을 통해 변경할 수 있다. 


### WebSecurityConfigurerAdapter 

WebSecurityConfigurerAdapter extends 하면서 Custom Config을 할 수 있다. 

사실 Security는 설정이 전부이다. 


동작 원리를 살펴보면 SpringBootWebSecurityConfiguration 에 의해서 자동 설정을 가하는데, 

만약 WebSecurityConfigurerAdapter Bean이 존재할 경우 기본 설정을 하지 않는다. 


### UserDetailsService

UserDetailsServiceAutoConfiguration에서도 UserDetailsService 해당 Bean이 존재하면 

자동 설정을 하지 않는다.


### Password Encoding 

DelegatingPasswordEncoder 해당 클래스에 의해 encoding을 한다.

PasswordEncoderFactories 에 의해서 설정된다.
 
DelegatingPasswordEncoder 내부 기본 전략으로 UnmappedIdPasswordEncoder을 갖고 있지만

실제 기본 전략은 "bcrypt" 을 사용한다. 

근데 신기한 점은 matches 할 때 extractId을 통해 PasswordEncoder를 찾는데 없으면 UnmappedIdPasswordEncoder가 

처리한다. 이 과정에서 무조건 Throw를 날린다..!! 


InitializeUserDetailsBeanManagerConfigurer.configure

PasswordEncoder passwordEncoder = getBeanOrNull(PasswordEncoder.class); 에서 

PasswordEncoder가 존재하지 않으면 역시나 PasswordEncoderFactories.createDelegatingPasswordEncoder 전략을 사용한다.








