<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Autorization</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>

<p style="color: red">${errorMessage}</p>
<form method="post" action="/input">
    <label>Login</label>
    <div class="row">
        <div class="col-xs-4 col-lg-offset-4">
            <input type="text" class="form-control " placeholder="Login" name="login">
        </div>
    </div>
    <br>
    <label>Password</label>
    <input type="text" class="form-control" placeholder="Password" name="password">
    <br>
    <input type="submit" value="Log in">
</form>


</body>
</html>
