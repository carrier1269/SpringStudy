### SpringStudy

- 남궁성 자바의 정석
https://www.youtube.com/@MasterNKS
0 ~ 171 완료

- 김영한 스프링 입문
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8
0 ~ 28 완료

## 짤짤이 내용 정리
### Session
- 1. 사용자 로그인 요청
  2. 사용자 ID, PW를 확인해서 사용자가 맞는지 확인
  3. 세션 ID 생성
  4. 사용자에게 세션 ID를 담아서 응답
  5. 세션 ID를 웹 브라우저에 저장
  6. 이제는 세션 ID를 담아서 사용자가 정보 요청
  7. 서버에는 세션 ID가 있으니 세선 저장소에서 세선 ID가 유효한지 검토
  8. 검증이 됐다면 사용자 정보를 받아온다.
  9. 사용자에게 응답
### Session +
- 서버가 한개에 최대 동접자가 100명이라고 하면 인원 초과시 서버를 증설하는데, 이를 통해 사용자가 다른 서버에 요청을 보낼 수 있게 된다.
- 이를 로드 밸런싱이라 하는데, 사용자가 첫 번째 서버로 접근해서 세션 ID를 발급받고 로드 밸런싱에 의해 다시 두 번째 서버로 접근하게 되면,
- 두 번째 서버의 세션 저장소에는 첫 번째 서버로 접근했던 사용자의 세선 ID 정보가 없기 때문에, 이전에 접속을 했던 사용자여도 서버가 달라서
- 접근한적이 없는 사용자로 인식하게 되는 문제가 발생하게 된다. 이를 해결하기 위해서 아래와 같은 해결책이 있다.
### Session Solution
- 1. Sticky Session을 사용해서 사용자가 전에 접근하였던 서버로만 보내게 된다.
  2. 모든 서버에 세션이 생성될 때 마다 세션을 복제시킨다.
  3. DB를 세션 저장소로 이용한다. -> 하지만 DB에 I/O가 발생하여 속도저하가 엄청나게 일어난다. 세션은 메모리에 저장시키고 불러오기 때문에 훨씬 빠르다.
  4. JWT를 사용하면 이 문제를 모두 해결할 수 있다.
 
### JWT
https://iseunghan.tistory.com/356

- TCP / UDP에 대한 간략 설명
  1. TCP 통신은 데이터를 전송하면 상대가 잘 받았는지 ACK 신호를 통해 응답한다.
  2. 반면 UDP 통신은 상대가 잘 받았는지 확인하지 않고 데이터를 전송하게 된다.
  3. UDP가 TCP에 비해 속도는 훨씬 빠르다.
  4. UDP는 언제 사용하냐면, 중간에 유실되는 데이터가 있더라도 상대방이 인식이 가능한 경우 사용한다.
  5. 예를 들어서, 초당 24 프레임인 동영상을 전송하는데, 24장의 사진중에 몇장이 유실되었어도 상대방이 동영상을 시청할 때 조금 버벅거릴뿐 영상을 이해할 수 있다.
  6. 전화를 할때 "안녕 반가워"라고 하는데 데이터가 유실되어서 안녕 반()워라고 들릴 수 있더라도, 상대방은 안녕 반가워라고 인지할 수 있는 경우.
 
### CIA
Confidentiality(기밀성) + Intergrity(무결성) + Availabililty(가용성)
- 개인정보, 민감한 정보를 인가된 사용자에게만 허가
- 내용의 변경이나 훼손없이 보존
- 항상 정상적으로 신뢰성 있는 서비스를 할 수 있는 상태

문서의 탈취나 위변조를 방지하기 위함
문서를 암호화하여 전달하는데, 암호화한 키를 상대방에게 전달하기 위한 문제성이 발생한다.

### RSA(암호화)
- public key
  오픈된 공간에 공개를 해도 문제가 발생하지 않는다.
- private key
  자기 자신만 알고 있어야 한다.
- RSA를 적용하여 A가 B에게 문서를 전송한 경우, B의 공개키를 문서에 적용하여 문서를 보냈기 때문에, C에서 문서를 탈취해도 문서를 열 수 없다.
- B의 공개키는 B가 B의 개인키로만 열 수 있기 때문이다.
- 누가 보낸건지 확인하기 위해서 A의 공개키로 암호화를 하여 확인 작업을 수행한다.

### JWT 간단 요약
- JWT로 주고받는 정보는 디지털 서명이 되어있기 때문에, 확인하고 신뢰할 수 있다.
- JWT는 HMAC 알고리즘을 사용한다.
- JWT는 정보를 암호화하여 주고받을 수 있지만, 서명된 토큰에 중점을 두는것이 중요하다.("내가 보낸게 맞아!"라는 서명, 인증에 중점을 둔다.)
- 토큰은 (.)의 기준으로 세부분으로 나뉘는데, (Header, Payload, Signature)으로 나뉜다.

#### Header
```
{
  "alg": "HS256",
  "typ": "JWT"
}
// 어떤 알고리즘으로 암호화를 하였는지
// 토큰의 타입이 무엇인지
```

#### Payload
```
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
// Payload에는 클레임을 포함하고 있다.
// 등록된 클레임 -> [iss(발급자), exp(만료시간), sub(제목), aud(대상), 등등]
// 공개 클레임 -> 원하는 값을 Key:value 형식으로 넣으면 된다.
// 비공개 클레임 -> 당사자 간에 정보를 공유하기 위해 생성된 맞춤 클레임
```

#### Signature
```
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
// Signature에는 Base64Url로 인코딩 된 header, payload, 시크릿 키를 가지고 HMAC256 알고리즘으로 암호화를 한 것
```

#### Client - 서버간 JWT 토큰 생성
id, pw를 통해 로그인을 하고 DB를 통해서 사용자를 확인한 후, 서버만 알고있는 secret_key를 통해서 JWT 토큰을 생성한 후 응답 헤더에 담아서 응답한다.

JWT Token Form (example)
```
// Before Encode
header : HS256
payload : {username : user1}
signature : HMACSHA256(
  base64Url(header) + "." +
  base64Url(payload),
  secret_key)

// After Encode -> not same only for example
eyHhbGci0iJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIi0iIxMjM0NTY30DkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.sflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

#### 토큰을 검증하는 방법
1. 클라이언트(사용자)에서 넘겨준 요청 헤더에서 JWT를 (.)의 기준으로 header, payload, signature 세가지로 분류를 한다.
2. header, payload를 base64UrlDecode한다.
3. 서버에 있는 secret_key를 통해서 HMAC256 알고리즘을 통해 암호화를 진행하면 signature가 생성이 되는데, 생성한 signature와 클라이언트에서 제공받은 JWT의 signature와 비교를 한다.

- (!) 이전에 Session에서 서버마다 세선 저장소를 생성함으로써 로드밸런싱을 통해 발생하는 문제가 있었는데, JWT 토큰을 사용하면 secret_key를 알고있으면 해당 서버에서 인증까지 끝마침으로써 암호화된 정보를 주고 받을 수 있기 때문에 Session이 아닌 JWT 토큰을 사용하는 것이다.

#### JWT 프로젝트 설정
```
1. Gradle
// https://mvnrepository.com/artifact/com.auth0/java-jwt
implementation group: 'com.auth0', name: 'java-jwt', version: '3.16.0'

2. application.yml -> 내가 나중에 application.properties 찾아서 바꿔야댐
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: "jdbc:mysql://localhost:3306/JwtTutorial"
    username: "root"
    password: "1234"

  jpa:
    hibernate:
      ddl-auto: create
    database: mysql
    show-sql: true

3. User 생성 및 열거형 User_roles 생성
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private USER_ROLES roles;

}

public enum USER_ROLES {
    ROLE_ADMIN, ROLE_USER
}

4. SecurityConfig
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Session을 사용하지 않고, Stateless 서버를 만들겠다는 의미
                .and()
                .addFilter(corsFilter)  // Cross-Origin 정책 사용 X 모든 요청 허용 ** @CrossOrigin과의 차이점 : @CrossOrigin은 인증이 없을 때 문제, 그래서 직접 시큐리티 필터에 등록!
                    .formLogin().disable()
                    .httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/user/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                    .antMatchers("/api/v1/manager/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                    .antMatchers("/api/v1/admin/**")
                        .hasRole("ADMIN")
                    .anyRequest().permitAll()
                ;
    }
}

5. CorsConfiguration
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 내 서버가 응답을 할 때 응답해준 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        config.addAllowedOrigin("*");       // 모든 ip에 응답 허용
        config.addAllowedHeader("*");       // 모든 header에 응답 허용
        config.addAllowedMethod("*");       // 모든 HTTP Method 요청 허용

         source.registerCorsConfiguration("/api/**", config);    // /api/** 로 들어오는 모든 요청들은 config를 따르도록 등록!

        return new CorsFilter(source);
    }
}
```

- build.gradle에서 implements를 통해서 라이브러리 추가
- application.properties 설정
- User 클래스 생성 및 열거형 User_roles 생성
- SecurityConfig를 통해 user/ or manager/ or adming/ 등등 요청에 따른 권한 설정 및 다른 보안정책 설정
- CorsConfig를 통해 도메인이 다른 서버로 리소스를 요청할때 CORS 정책을 위반하여 발생하는 에러를 해결한다.
- CorsConfig Solution -> CorsConfiguration 객체를 생성하여 원하는 요청에 대해서 허용해주면 된다.
- --> HTTP OPTION으로 예비 요청을 보낸 후, 서버에서 요청에 허용을 한다는 응답을 받으면 GET or POST 방식으로 리소스 요청을 보내는 방법.

#### JWT Token 인증 방식
- Session을 사용했을 때 서버에 두는 세션 저장소 및 여러 서버일때의 문제가 발생하는 것을 해결할 수 있다.
- 쿠키를 사용하지 않아도 되기 때문에, 쿠키를 탈취당했을 때 발생하는 보안 취약점들이 사라지게 된다.
- 서버가 여러대여도 secret_key값만 알고있으면 토큰을 인증할 수 있다.

#### Session은 동일 도메인에서만 !!
- 서버 도메인이 "www.naver.com"인 경우 a의 도메인이 "111.222..."인 경우 서버로 요청 시 쿠키가 날라가지 않는다. 서버에서 쿠키를 거부.
- javascript에서 Ajax를 통해 강제로 쿠키를 담아 보낼 수 있는데, 서버에서 HTTP Only를 설정해놓으면, javascript 요청이 들어오면 거부하게 된다.
- HTTP Only = false로 풀어주게 되면 외부에서 javascript로 장난을 많이 치기 때문에 대부분 HTTP Only = true로 설정하는 편이다.

#### Authorization
서버로 요청을 보낼 때 요청 헤더에 Authorization : <type><credentials> 를 담아서 보내는데 type에 여러가지가 있지만 대표적으로 두가지가 있다.
1. Basic
- 사용자 ID와 PW를 Base64로 인코딩한 값을 토큰으로 사용하는데, 토큰이 노출되면 ID, PW가 노출되는 것이므로 보안에 취약.
2. Bearer
- JWT같은 OAuth 토큰을 사용하는데, Basic과 달리 토큰에 ID, PW를 넣지 않는다.
- 로그인 시 토큰을 부여받고, 이후 요청 헤더에 토큰을 실어서 보낸다.
- JWT랑 비슷함.

#### Spring Security Filter
![image](https://github.com/carrier1269/SpringStudy/assets/58325946/14d63e5f-f118-439b-a28f-01d9b1de64c1)
```
public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 1");
        filterChain.doFilter(servletRequest, servletResponse);  // 다음 필터로 넘어가라는 의미
    }
}
```

- Filter 인터페이스를 구현하고, 해당 필터를 처리하고 다음 필터로 넘겨주는 doFilter 메서드를 호출하면 된다.

SecurityConfig에 추가하는 방법
```
http.addFilter(new MyFilter1());
// 위와 같이 작성하게 되면 "MyFilter1은 SpringSecurityFilterChain에 등록되지 않았으니 등록하고 싶으면 addFilterBefore or addFilterAfter를 사용하라!" 와 같은 에러가 발생한다.

http.addFilterBefore(new MyFilter1(), UsernamePasswordAuthenticationFilter.class);
// UsernamePasswordAuthenticationFilter 직전에 MyFilter가 걸리도록 한다.

http.addFilterAfter(new MyFilter1(), UsernamePasswordAuthenticationFilter.class);
// UsernamePasswordAuthenticationFilter 이후에 MyFilter가 걸리도록 한다.
```

FilterConfig를 생성하여 Spring Bean에 등록하는 방법
```
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");  // 모든 요청에 대해서 필터 적용
        bean.setOrder(0);   // 낮은 숫자일수록 우선순위

        return bean;
    }
}
```
```
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
		...
        bean.setOrder(0);   // 낮은 숫자일수록 우선순위
		..
```

첫 번째에서 SecurityConfig에 등록한 것
```
http.addFilterBefore(new MyFilter2(), UsernamePasswordAuthenticationFilter.class)
```

![image](https://github.com/carrier1269/SpringStudy/assets/58325946/56ef015c-12ae-4168-819a-f5ab06785061)

#### 필터를 통해서 요청 헤더 토큰 확인하는 방법
```
public class MyFilter3 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setCharacterEncoding("utf-8");

        // 만약, token을 검증하여, Controller에 접근 여부 설정!
        if (req.getMethod().equals("POST")) {
            String auth_header = req.getHeader("Authorization");

// 앞에서 정리했던 내용중에 헤더 요청을 보내는 방법은 여러가지 방법들이 있지만, 대표적으로 basic과 bearer가 있다.
// 요청한 헤더에서 토큰값을 검증하여 필터를 거치고, 값이 다르다면 실행되지 않게 만드는 방법.

            if(auth_header.equals("secret")) {
                filterChain.doFilter(req, res);
            } else {
                PrintWriter writer = res.getWriter();
                writer.println("인증 안됨");
            }
        } else {
        	filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
```

#### Postman을 통해 request를 해서 필터 동작을 확인
![image](https://github.com/carrier1269/SpringStudy/assets/58325946/03eb9074-f47f-4c90-9c65-8e1b93ad4159)

#### 로그인 요청이 아닌 로그인 이후 발급한 JWT 토큰을 통해 필터를 거쳐 클라이언트(사용자)를 검증하는 방법
![image](https://github.com/carrier1269/SpringStudy/assets/58325946/e3c57cb6-7e4d-4b0a-888c-13bf80232b86)
이것은 요청 헤더 basic 방식인데, 보안면에서 좋지 않기 때문에 jwt를 사용하는 방법을 해보자.

```
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

//HttpServletRequest를 통해서 request를 통해 요청한 헤더를 받아오게 됩니다.
    String jwt_Header = request.getHeader("Authorization");

    // 토큰 값이 올바르게 들어있는지 체크! -> jwt_header값이 null이 아니거나, Bearer로 시작할 때
    if(jwt_Header != null && jwt_Header.startsWith("Bearer")) {

        // JWT 추출 -> 가져온 jwt_header의 앞에 있는 Bearer 문자열을 공백으로 바꾸어 필요한 값만 사용하도록 하고, trim() 메소드를 사용하여 문자열을 나눕니다.
        String jwtToken = jwt_Header.replace("Bearer", "").trim();

        // JWT Verify (검증) -> 검증 실패 시 exception 발생
	// secret_key를 HMAC 알고리즘을 사용하여 가져온 jwtToken의 값과 비교를 하고 갖다면 클레임에서 username을 가져와 문자열의 형태로 만들어 username 변수에 저장합니다.
        String username = JWT.require(Algorithm.HMAC256("secret")).build().verify(jwtToken).getClaim("username").asString();

        // Verify 통과? -> 서명이 완료되었다는 뜻.
        // username이 비어있진 않은지 체크
        if (username != null && !username.equals("")) {
            UserDetails userDetails = accountService.loadUserByUsername(username);

            // AuthenticationManager로 인증을 하면 실제 로그인을 할때에 필요한 작업이나,
            // Authentication authenticate = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));

            // 현재 우리는 Token 서명으로 무결성을 검증하였기 때문에 username을 가지고 강제로 Authentication 을 만들어 securityContextHolder에 넣어주면 됩니다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            // 세션 저장 (권한 관리를 위해서)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    doFilter(request, response, chain);

}
```

#### Entity
![image](https://github.com/carrier1269/SpringStudy/assets/58325946/0b1235d7-a437-4d46-b601-e2981dcbe9de)
- Spring 프로젝트를 보면 Entity를 생성하는 것을 많이 볼수있다.
- 2차원 테이블의 구조로 이루어진 것을 Entity라고 한다.
- Entity 클래스를 작성할 때는 애너테이션을 사용하여 @Entity로 클래스 앞에 명시하여 해당 클래스가 Entity임을 명시한다.
- Entity 일관성 보존을 위해서 Setter의 사용을 지양하는 편이다.
- JPA에서 정의한 필드를 토대로 테이블을 생헝한다.

#### Entity Annotation
@Entity
- 클래스 선언시 해당 클래스가 Entity임을 명시한다.
- Entity 클래스는 JPA에서 정의한 필드를 기반으로 테이블을 생성한다.

@Builder
- Entity 객체 생성 시 Builder Pattern을 생성한다.

@AllArgsConstructor
- 모든 필드를 파라미터로 받는 생성자를 생성한다.
- 디버깅이 어렵고, 많은 버그들을 발생시킬 수 있으므로 해당 애너테이션 사용을 지양한다.
- 생성자를 직접 만들고 생성자에 @Builder를 사용하는 것을 권장하는 편이다.

@NoArgsConstructor
- 파라미터가 없는 기본 생성자를 생성한다.
- 초기값이 final 필드인경우 컴파일 에러가 발생한다.
- access -> 속성값이 protected인 경우(@NoArgsConstructor(access = AccessLevel.PROTECTED)), 외부에서 무분별하게 해당 Entity의 객체 생성을 방지한다.
- public으로 설정할 경우 객체 생성시 안전성 저하를 유발시킨다.

@Getter
- 필드값 조회를 위한 Getter 생성

@ToString
- 클래스에서 선언한 모든 필드를 출력하는 toString 메소드를 생성한다.
- exclude 속성을 사용하면 특정 Field는 출력하지 않도록 할 수 있다.

@Table
- Entity와 Mapping할 Table을 지정한다.
1. name(String, Optional) -> mapping할 테이블 이름 지정, default는 Entity이름
----> Example >> @Table(name = "테이블 이름")
2. catalog(String, Optional) -> DB 카탈로그 mapping
3. indexes(index[], Optional) -> DB index mapping
4. uniqueConstraints(UniqueConstraint[], Optional) -> DDL 생성 시 Unique 제약사항 지정

@ID
- Entity의 Primary Key값 지정

@GeneratedValue(strategy = GenerationType.IDENTITY)
- 전에 실습했었던, ID값이 자동으로 지정되는 역할과 같은 비슷한 역할을 한다.

@Column
1. name(String, Optional) -> Column 이름 지정, default는 해당 객체 이름으로 설정한다.
2. nullable(Boolean, Optional) -> 해당 필드값이 반드시 입력 받아야 할지 여부를 결정한다. 기본값은 true
3. length(int, Optional) -> 문자열의 길이를 제한한다. String Type에서만 사용이 가능하다.

@ManyToOne
- 하나의 Entity와 다른 N개의 Entity간의 관계 형성을 지원한다.

Entity 작성 예시
```
@Entity
@Table(name = "employee") 
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 필수 입력 & 10글자 초과 입력불가
    @Column(nullable = false, length = 10)
    private String name;
    @Column
    private int age; // 객체 이름과 다르게 column 이름 개별지정
    @Column(name = "salary") 
    private int pay; 
}
```

#### DI / IOC container
- DI -> 내부에서 객체를 생성하는 것이 아닌 외부에서 객체를 생성하여 주입하는 방식.
- 작동시킬때마다 내부적으로 새로운 객체가 생성이 되는 것을 방지하기 위해서 스프링은 이런 방법을 사용한다.
- 외부에서 생성한 객체 데이터를 IOC Container라고 함. (학습한 정보가 부족해서 추가적으로 공부해서 보충 예정)

#### 8/19 ++
https://e-una.tistory.com/72
dto + entity(//) + multipart data + swagger

#### 쿠키
웹서버에서 웹브라우저에게 보내어 저장했다가, 웹서버에서 부가적인 정보 요청시 다시 서버로 보내는 문자열 정보, key=value 형식의 문자열 데이터 묶음이며, 요청할때 정보를 보내어 사용자를 식별하는 정보, 쿠키의 활용으로써는 크게 세선관리, 개인화, 트래킹에 사용
1. 세선관리
- 서버에 저장해야 할 로그인, 장바구니, 게임 스코어 등의 정보 관리
2. 개인화
- 사용자 선호, 테마 등 사용자의 개인 설정 저장 및 관리
3. 트래킹
- 분석 및 광고 개제를 위해서 웹사이트 내 사용자 행동 기록, 관리

#### 쿠키 & 세션 과 jwt 의 장단점
||장점|단점|
|---|---|---|
|**Cookie & Session**|쿠키만 사용하는 방식보다 보안 향상 및 세션 서버 통제 및 네트워크 부하 낮음|세션 저장소 사용으로 인한 서버 부하 및 로드 밸런싱에 의한 세션 인증 문제 발생|
|**jwt(Json Web Token)**|인증을 위한 별도의 저장소가 필요 없고, 빠른 인증 처리 및 확장성이 우수함, 세선을 사용했을때 다중 서버에서의 로드 밸런싱에 의해서 세션을 인증하는데 어려움이 있는데, jwt를 사용하면 secret_key를 hmac 알고리즘을 사용한 암호화된 signature을 비교하면 된다는 장점이 있음.|토큰의 길이가 늘어날수록 네트워크가 부하되고, 특정 토큰을 강제로 만료 시키기가 어렵다|

#### 8/23
spring security + jwt example

https://velog.io/@jkijki12/Spirng-Security-Jwt-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0

#### @RequiredArgsConstructor 애너테이션
@RequiredArgConstructor 애너테이션을 사용하여 생성자를 주입하는 방법
- Lombok plugin을 사용함
- https://velog.io/@developerjun0615/Spring-RequiredArgsConstructor-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EC%A3%BC%EC%9E%85
- gradle에 implementation 'org.projectlombok:lombok' 추가해줘야 lombok애너테이션 사용 가능

