package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    // 기존에는 void였지만, 이제는 MyView를 반환한다.
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
