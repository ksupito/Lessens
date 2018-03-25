<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="/js/ajaxResultTable.js"></script>
    <link href="/css/styleResult.css" rel="stylesheet">
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
            <td id="id"><c:out value="${user.id}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td>
                <button id="buttonShowMore" onclick="showMore(this)">Show More</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${requestScope.countPages > 1}">
    <c:forEach begin="1" end="${requestScope.countPages}" varStatus="loop">
        <button id="button${loop.count}" onclick="changePageNumber(${ loop.count })">${ loop.count }</button>
    </c:forEach>
    </form>
</c:if>
<a href="/input">Назад</a>
<div id="form" class="popup-position">
    <div id="popup-wraper">
        <div id="popup-container">
            <span id="showResult"></span><br>
            <p id="po"></p>
            <button id="closePopup" onclick="toggle_visibility('form')">Close</button>
        </div>
    </div>
</div>
<div id="loader" class="loader">
    <p><img src="/img/tenor.gif"></p>
</div>
</body>
</html>