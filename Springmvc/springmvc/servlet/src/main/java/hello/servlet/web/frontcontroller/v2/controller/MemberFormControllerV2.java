package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.v1.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // command + option + N으로 변수 정의 + 리턴을 합칠 수 있다.
        return new MyView("/WEB-INF/views/new-form.jsp");

    }
}
