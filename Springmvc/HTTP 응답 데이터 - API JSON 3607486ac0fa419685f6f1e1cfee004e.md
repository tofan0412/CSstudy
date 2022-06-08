# HTTP 응답 데이터 - API JSON

### ResponseJsonServlet

```java
package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Content-Type : application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // {"username":"kim", "age":20}
        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);

    }
}
```

→ 나중에는 그냥 return helloData;와 같이 작성하면 끝난다..

### 참고

`application/json` 타입은 스펙상 인코딩 방식으로 utf-8 형식을 사용한다. 그래서 스펙에서 `charset=utf-8` 과 같은 추가 파라미터를 지원하지 않는다. 따라서 `application/json` 이라고만 사용해야지, `application/json;charset=utf-8` 이라고 전달하는 것은 의미 없는 파라미터를 추가한 것이 된다. 

response.getWriter()를 사용하면 추가 파라미터를 자동으로 추가해버린다. 이 때는 response.getOutputStream()으로 출력하면 그런 문제가 없다.