# HttpServletResponse - 기본 사용법

## HttpServletResponse의 기본 역할

- HTTP 응답 메시지 생성
- HTTP 응답코드 지정
- 헤더 생
- 바디 생성

### 편의 기능 제공

- Content-Type, 쿠키, Redirect

> HTTP Response Message의 응답코드, 헤더를 직접 설정해보자.
> 

```java
package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * https://localhost:8080/response-header
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 응답 코드 수정하기
        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // 응답 HTTP 메시지의 헤더를 설정할 수 있다.
        // [response-header]
        resp.setHeader("Content-Type", "text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");       // 내가 원하는 임의의 헤더를 설정할 수도 있다.
				
        // [Message Body 설정하기]
        PrintWriter writer = resp.getWriter();
        writer.println("안녕하세요!");

    }
}
```

이제 `[http://localhost:8080/response-header](http://localhost:8080/response-header)` url을 입력하면 다음과 같은 응답 객체 정보를 **개발자 도구**에서 확인할 수 있다.

![Untitled](HttpServletResponse%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%203458be3d40014e1899d9be5a184f719b/Untitled.png)

![Untitled](HttpServletResponse%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%203458be3d40014e1899d9be5a184f719b/Untitled%201.png)

### 편의 메서드 - Content

setHeader의 일부 내용은 다음과 같이 변경할 수도 있다.

```java
private void content(HttpServletResponse response) {
    // Content-Type: text/plain;charset=utf-8
    // Content-Length: 2
    // response.setHeader("Content-Type", "text/plain;charset=utf-8");

    // setHeader의 내용을 아래와 같이 할 수도 있다.
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    // response.setContentLength(2); //(생략시 자동 생성)
}

// Content-Length는 생략할 수도 있고, 내가 직접 작성할 수도 있다. println()을 하게 되면 출력문이 OK인 경우 3이 나오고, print()를 하면 2가 나온다.
// Content-Length는 보통 생략한다. 
```

이제 앞서 작성했던 `resp.setHeader("Content-Type", ...)` 에 주석 처리를 해도 정상적으로 동작하는 것을 확인할 수 있다.

```java
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 응답 코드 수정하기
        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // 응답 HTTP 메시지의 헤더를 설정할 수 있다.
        // [response-header]
//        resp.setHeader("Content-Type", "text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");       // 내가 원하는 임의의 헤더를 설정할 수도 있다.

        // [Header 편의 메서드]
        content(resp);

        PrintWriter writer = resp.getWriter();
        writer.println("안녕하세요!");

    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // setHeader의 내용을 아래와 같이 할 수도 있다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); //(생략시 자동 생성)
    }
}
```

### 쿠키 관련 설정하기

```java
private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        // 위 내용은 아래와 같이 작성해도 된다.
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 다만 매번 이렇게 작성하는 것은 귀찮으므로, 아래의 Cookie 객체를 이용해 setHeader를 이용하지 않더라도 편리하게 쿠키를 설정할 수 있다.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
```

`service` 메서드에 `cookie(resp)` 를 추가하면 된다.

![Untitled](HttpServletResponse%20-%20%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%203458be3d40014e1899d9be5a184f719b/Untitled%202.png)

⇒ 위와 같이 response 객체의 header에 정상적으로 쿠키가 들어왔음을 확인할 수 있다.

### 리다이렉트하기

```java
  private void redirect(HttpServletResponse response) throws IOException {
      //Status Code 302
      //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
      // 위와 같이 해도 된다. 하지만 이를 간단하게 아래와 같이 작성할 수 있다.
      response.sendRedirect("/basic/hello-form.html");
  }
```