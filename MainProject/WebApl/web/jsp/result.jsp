<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="/js/ajaxResultTable.js"></script>
</head>
<body>
<noscript>
    JavaScript is off in your browser
</noscript>
<table border="1" cellspacing="0">
    <tr>
        <th>id</th>
        <th>last_name</th>
        <th>first_name</th>
    </tr>
    <tbody id="table">
    <c:forEach var="user" items="#{requestScope.listOfUser}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${requestScope.countPages > 1}">
    <p class="button">
        <c:forEach begin="1" end="${requestScope.countPages}" varStatus="loop">
            <button id="button${loop.count}" name="${ loop.count }">${ loop.count }</button>
        </c:forEach>
    </p>
    </form>
</c:if>
<a href="/input">Назад</a>
</body>
</html>
