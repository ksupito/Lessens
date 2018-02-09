<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Autorization</title>
</head>
<body>
<p style="color: red">${errorMessage}</p>
<form method="post" action="/input">
    <p>Login <input type="text" name="login"></p>
    <p>Password <input type="text" name="password"></p>
    <input type="submit" value="Log in">
</form>
</body>
</html>
