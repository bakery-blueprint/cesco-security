# Web Application Security

## Ignoring

WebSecurity.ignoring을 통해 request를 무시할 수 있다. 

Filter 개수가 0이기 때문에 성능적으로 이점이 있다.


### DefaultSecurityFilterChain

FilterChain을 의미한다. 내부적으로 requestMatcher/ Filter 를 갖고있다. 


WebSecurityConfiguration 설정에 의해 springSecurityFilterChain가 생성된다. 

그 과정에서 AbstractConfiguredSecurityBuilder.doBuild를 호출한다.

이어서 WebSecurity.performBuild 호출한다. 

Ignoring 케이스에 대해서 DefaultSecurityFilterChain의 기본 생성자를 사용한다. 

~~~java
public DefaultSecurityFilterChain(RequestMatcher requestMatcher, Filter... filters) {
		this(requestMatcher, Arrays.asList(filters));
	}
~~~
Arrays.asList()로 0개가 설정된다. 


## Async

### AsyncManagerIntegrationFilter

request attribute에 WEB_ASYNC_MANAGER_ATTRIBUTE WebAsyncManager을 박아준다.

WebAsyncManager taskExecutor 전략이 SimpleAsync라.. 기본전략 보단 다르게 Custom하는 것이 좋다.

아무튼 여기까지는 시큐리티랑 관련은 없다.

SecurityContextCallableProcessingInterceptor 이 친구를 넣어주는게 조금 특별한 것이다. 

### SecurityContextCallableProcessingInterceptor

SecurityContext를 스레드로컬에서 꺼내어서 갖고있다, 스레드가 변경된 경우 해당 SecurityContext을 넣어주는 구조이다.

이런 방법은 결국 리엑티브에서 많이 사용하게 된다...!!


RequestMappingHandlerAdapter 이 친구 내부에 보면 

HandlerMethodReturnValueHandlerComposite 해당 필드를 갖고있는데 

말그대로 Controller에서 return type에 따라 처리하는 Handler이다. 

여기서 우리가 많이 사용하는 @ResponseBody Handler도 등장한다..

아무튼 이 Callable 의 경우 CallableMethodReturnValueHandler 이친구가 처리한다. 

~~~java
WebAsyncUtils.getAsyncManager(webRequest).startCallableProcessing(callable, mavContainer);
~~~

startCallableProcessing의 내부에서 taskExecutor을 사용해서 submit 하는 것이 나온다..!!


## 과제 

### WebAsyncManager의 taskExecutor을 변경해보자.

### WebAsyncManager에 CallableProcessingInterceptor를 사용해보자. 



