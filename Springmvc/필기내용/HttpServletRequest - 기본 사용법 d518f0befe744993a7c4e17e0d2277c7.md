# HttpServletRequest - 기본 사용법

다음과 같이 서블릿 객체를 정의하자.

```java
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");

        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocal() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }
}
```

이후 서버를 재가동하고 해당 URL을 입력하면 다음과 같이 콘솔창에 출력된다.

```
--- REQUEST-LINE - start ---
request.getMethod() = GET
request.getProtocal() = HTTP/1.1
request.getScheme() = http
request.getRequestURL() = http://localhost:8080/request-header
request.getRequestURI() = /request-header
request.getQueryString() = null
request.isSecure() = false
--- REQUEST-LINE - end ---
```

쿼리 스트링을 추가하면 다음과 같다. (`localhost:8080/request-header?username=kim`)

```
--- REQUEST-LINE - start ---
request.getMethod() = GET
request.getProtocal() = HTTP/1.1
request.getScheme() = http
request.getRequestURL() = http://localhost:8080/request-header
request.getRequestURI() = /request-header
request.getQueryString() = username=kim
request.isSecure() = false
--- REQUEST-LINE - end ---
```

### 헤더 정보 출력하기

다음과 같은 코드를 추가하자.

```java
//Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // 예전 방법. 헤더 정보를 출력하는 방법이다.
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) { // 다음 값이 있다면
            String headerName = headerNames.nextElement();  // 다음 값을 출력한다.
            System.out.println(headerName + ": " + headerName);
        }

        System.out.println("--- Headers - end ---");
    }

// 다음과 같이 간결하게 작성할 수 있다.
//Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println("headerName : " + headerName));

        System.out.println("--- Headers - end ---");
    }
```

서버 재가동 후, URL을 호출하면 다음과 같이 헤더 정보가 나오는 것을 확인할 수 있다.

```
--- Headers - start ---
host: host
connection: connection
cache-control: cache-control
sec-ch-ua: sec-ch-ua
sec-ch-ua-mobile: sec-ch-ua-mobile
sec-ch-ua-platform: sec-ch-ua-platform
upgrade-insecure-requests: upgrade-insecure-requests
user-agent: user-agent
accept: accept
sec-fetch-site: sec-fetch-site
sec-fetch-mode: sec-fetch-mode
sec-fetch-user: sec-fetch-user
sec-fetch-dest: sec-fetch-dest
accept-encoding: accept-encoding
accept-language: accept-language
--- Headers - end ---
```

이제 헤더를 편리하게 조회해보자.

```java
private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()    // 모든 Accept-Language 정보를 꺼낸다.
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale()); // 우선순위가 가장 높은 로케일 정보를 출력한다.
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {    // 쿠키 정보로 HTTP Header에 포함되어 있다.
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());    // get방식의 경우 바디가 비어있기 때문에, 바디의 컨텐츠 타입을 알 수 없다.
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }
```

다음의 출력 결과를 얻는다.

```
--- Header 편의 조회 start ---
[Host 편의 조회]
request.getServerName() = localhost
request.getServerPort() = 8080

[Accept-Language 편의 조회]
locale = ko
locale = en_US
locale = en
locale = ja
request.getLocale() = ko

[cookie 편의 조회]

[Content 편의 조회]
request.getContentType() = null
request.getContentLength() = -1
request.getCharacterEncoding() = UTF-8
--- Header 편의 조회 end ---
```

기타 부가정보..

```java
//기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
```