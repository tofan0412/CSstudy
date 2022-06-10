# HTTP 요청 데이터 - POST HTML Form

다음과 같이 HTML 문서를 작성하자.

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<form action="/request-param" method="post">
  username: <input type="text" name="username" />
  age:      <input type="text" name="age" />
  <button type="submit">전송</button>
</form>
</body>
</html>
```

**request.getParameter()는 쿼리 파라미터 방식과 POST 방식 모두를 지원한다.**

→ GET방식으로 전송했을 때와 POST방식으로 전송했을 때 모두 `Content-Type` 은 `x-www-form-urlencoded` 이다. 따라서 `request.getParameter()` 를 그대로 사용할 수 있다. 클라이언트(브라우저)에겐 두 방식에 차이가 있지만, 서버 입장에서는 둘의 형식이 동일하다.

> 참고)
`Content-Type` 은 HTTP Message Body의 데이터 형식을 지원한다. 
GET URL 쿼리 파라미터 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP Message Body를 사용하지 않기 때문에 content-type이 없다. (null)
POST HTML Form 형식으로 데이터를 전달하면 HTTP Message Body에 해당 데이터를 포함해서 보내기 때문에 반드시 Body에 저장된 데이터가 어떤 형식인지를 알리는 Content-Type을 꼭 지정해야 한다. 이렇게 Form으로 데이터를 전송하는 형식을 `application/x-www-form-urlencoded` 라 한다.
>