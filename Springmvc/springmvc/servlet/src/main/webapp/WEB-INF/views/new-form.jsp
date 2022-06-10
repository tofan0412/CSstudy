<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save]
만약 save라고 작성하면 현재 경로(http://localhost:8080/servlet-mvc/members/save로 이동하게 되며,
/save라고 작성하면 http://localhost:8080/save로 이동하게 된다.
-->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>

</body>
</html>
