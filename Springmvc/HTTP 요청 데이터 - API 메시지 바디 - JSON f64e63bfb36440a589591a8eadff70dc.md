# HTTP 요청 데이터 - API 메시지 바디 - JSON

이번에는 단순 텍스트가 아닌, JSON 형식으로 데이터를 전달해보자.

## JSON 형식 전송

### 1. JSON 형식으로 파싱할 수 있게끔, 객체를 하나 만든다.

```java
package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class HelloData {
    private String username;
    private int age;
}
```

### 2. 다음과 같은 서블릿 파일을 만든다.

```java
package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // 여기까지는 plain text값을 변환하려는 것과 동일하다.

        System.out.println("message = " + messageBody);

    }
}
```

이후 포스트맨에서 다음과 같이 요청을 만들고 보내보자.

![Untitled](HTTP%20%E1%84%8B%E1%85%AD%E1%84%8E%E1%85%A5%E1%86%BC%20%E1%84%83%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%90%E1%85%A5%20-%20API%20%E1%84%86%E1%85%A6%E1%84%89%E1%85%B5%E1%84%8C%E1%85%B5%20%E1%84%87%E1%85%A1%E1%84%83%E1%85%B5%20-%20JSON%20f64e63bfb36440a589591a8eadff70dc/Untitled.png)

다음과 같은 결과를 콘솔창에서 확인할 수 있다. (줄바꿈까지 반영된다는 점을 기억하자.)

```
message = {
    "username" : "hello",
    "age" : 20
}
```

### 이제 단순 텍스트로 읽는 것이 아닌, JSON 파싱을 통한 객체로 데이터를 읽어보자.

```java
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // Jackson을 활용하자.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // 여기까지는 plain text값을 변환하려는 것과 동일하다.

        System.out.println("message = " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

    }
}
```

다시 한번 요청을 보내게 되면 다음과 같은 내용을 확인할 수 있다.

```
message = {
    "username" : "hello",
    "age" : 20
}
helloData = HelloData(username=hello, age=20)
```

### 참고1)

> JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 JSON 변환 라이브러리를 추가해서 사용해야 한다. springboot로 Spring MVC를 선택하면 기본으로 Jackson 라이브러리(`ObjectMapper`)를 함께 제공한다.
> 

### 참고2)

> HTML form 데이터도 Message Body를 통해 전송되므로 직접 읽을 수 있다. 하지만 편리한 파라미터 조회기능(`request.getParameter` )을 이미 제공하기 때문에 파라미터 조회 기능을 사용하면 된다.
>