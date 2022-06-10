package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 다양한 컨트롤러를 만들 것이기 때문에, 다형성에 의존해 인터페이스를 먼저 만든다.
 */
public interface ControllerV1 {
    // 각 인터페이스는 이 인터페이스를 구현하면 된다.
    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
