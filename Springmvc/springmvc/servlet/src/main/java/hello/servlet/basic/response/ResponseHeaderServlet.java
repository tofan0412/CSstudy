package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
//        resp.setHeader("Content-Type", "text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");       // 내가 원하는 임의의 헤더를 설정할 수도 있다.

        // [Header 편의 메서드]
        content(resp);
        cookie(resp);
        redirect(resp);


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

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        // 위 내용은 아래와 같이 작성해도 된다.
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 다만 매번 이렇게 작성하는 것은 귀찮으므로, 아래의 Cookie 객체를 이용해 setHeader를 이용하지 않더라도 편리하게 쿠키를 설정할 수 있다.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        // 위와 같이 해도 된다. 하지만 이를 간단하게 아래와 같이 작성할 수 있다.
        response.sendRedirect("/basic/hello-form.html");
    }
}
