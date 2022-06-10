<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/06/08
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response는 import하지 않더라도 그냥 사용 가능하다.
    // 단 명칭은 request, response로 고정된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));   // 나이의 유형은 int인데, getParameter의 응답 유형은 항상 String이다.

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id= <%= member.getId()%></li>
    <li>username= <%= member.getUsername()%></li>
    <li>age= <%= member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
