<%@ page import="newProject.Main2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Time is: <%= session.getAttribute("timeOfSession") %></p>
    <p>Data is: <%= session.getAttribute("valueOfSession") %></p>
</body>
</html>
