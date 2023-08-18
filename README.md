### SpringStudy

- 남궁성 자바의 정석
https://www.youtube.com/@MasterNKS
0 ~ 171 수강

- 김영한 스프링 입문
https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8
0 ~ 28 수강

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
