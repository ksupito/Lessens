<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0">
    <tr>
        <th>id</th>
        <th>last_name</th>
        <th>first_name</th>
    </tr>
    <c:forEach var="user" items="#{requestScope.listOfUser}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="/input">Назад</a>
</body>
</html>
