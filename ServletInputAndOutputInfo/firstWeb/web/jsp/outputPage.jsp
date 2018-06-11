<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p> <%= session.getAttribute("timeOfSession") %></p>
    <p> <%= session.getAttribute("valueOfSession") %></p>
</body>
</html>
