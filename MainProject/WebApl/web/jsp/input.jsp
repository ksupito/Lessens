<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Input</title>
</head>
<body>
<p style="color: red">${errorMessage}</p>
<form method="post" action="/result">
    <p>Введите фамилию <input type="text" name="lastName"></p>
    <input type="submit" value="Найти">
</form>
</body>
</html>
