package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));   // 나이의 유형은 int인데, getParameter의 응답 유형은 항상 String이다.

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관해야 한다.
        // 내부에 Map이 존재한다.
        req.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
