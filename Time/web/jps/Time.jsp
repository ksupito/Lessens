<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Время : </h1>
<%! Date date = new Date();%>
<%= date.toString()%>
</body>
</html>
