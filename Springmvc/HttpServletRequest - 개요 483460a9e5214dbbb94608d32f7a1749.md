# HttpServletRequest - 개요

## HttpServletRequest의 역할

> HTTP 요청 메시지를 개발자가 직접 파싱해서 사용해도 되지만, 매우 불편하고 번거로울 것이다. 서블릿은 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다. 그리고 그 결과를 `HttpServletRequest` 객체에 담아서 제공한다.
> 

```xml
POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

username=kim&age=20
```

- start line
    - HTTP 메서드 종류
    - URL
    - 쿼리 스트링
    - 스키마, 프로토콜
- Header
    - Host
    - 컨텐츠 유형
- Body
    - 헤더에서 한 칸 띄우고 있는 나머지 내용
    
1. HttpServletRequest는 “임시 저장소 기능"을 한다. 해당 HTTP 요청이 끝날 때까지 유지되는 임시 저장소 기능을 한다.
2. 세션 관리 기능도 존재한다.

### 중요

> 가장 중요한 점은 이 객체들이 HTTP 요청, 응답 메시지를 편리하게 사용할 수 있도록 도와주는 객체라는 것이다. 따라서 이 기능에 대해 깊이 있는 이해를 하려면 HTTP Spec이 제공하는 요청, 응답 메시지 자체를 이해해야 한다.
>