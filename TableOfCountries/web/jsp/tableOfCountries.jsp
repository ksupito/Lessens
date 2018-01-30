<%@ page import="java.util.Locale" %>
<%@ page import="jspHelper.Countries" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0">
    <tr>
        <th bgcolor="#f5f5dc">Код</th>
        <th bgcolor="#f5f5dc">Наименование</th>
    </tr>
    <c:set var="friends" value='<%=Countries.createMapOfCountrues(Locale.getISOCountries())%>'/>
    <c:forEach var="entry" items="${friends}">
        <tr>
            <td align="center"><c:out value="${entry.key}"/></td>
            <td align="center"><c:out value="${entry.value}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
