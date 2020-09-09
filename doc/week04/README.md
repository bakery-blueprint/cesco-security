## [SecurityContextPersistenceFilter](https://kangwoojin.github.io/programing/spring-security-basic-security-context-persistence-filter/)
- 여러 요청간에 SecurityContext 공유 할 수 있게 해주는 Filter
- SecurityContextRepository
- 해당 필터가 2번째에 적용되어 있는 이유
    - 다른 Filter들이 skip 할 수 있도록 SecurityContext 값을 주입해서 다른 filter들이 skip 될 수 있게 한다.

    

## [HeaderWriterFilter](https://kangwoojin.github.io/programing/spring-security-basic-header-wrtier-filter/)
- security 관련 header를 추가 해주는 역할
- custom header writer 추가


## [CsrfFilter](https://kangwoojin.github.io/programing/spring-security-basic-csrf-filter/)
- security에서 기본적으로 csrf check는 csrf token을 이용해서 확인
- 모든 요청이 아닌 resource 변경하는 요청에 대해서만 적용


## 과제
1. SecurityContextRepository를 Custom 해보자
- localMapSecurityContextRepository를 개발하여 username을 기반으로 SecurityContext를 가져올 수 있게 변경

2. SecurityContext를 logging 하는 header writer를 HeaderWriterFilter에 추가해보자

3. 
