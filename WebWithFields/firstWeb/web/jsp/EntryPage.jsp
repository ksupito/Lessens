<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p style="color: red">${errorMessage}</p>
<p style="color: green">${cancelMessage}</p>
<form method="post" action="/output">
    <p>Time:<input type="text" name="timeOfSession"></p>
    <p>Data:<input type="text" name="valueOfSession"></p>
    <input type="submit" value="Go!">
</form>
</body>
</html>
