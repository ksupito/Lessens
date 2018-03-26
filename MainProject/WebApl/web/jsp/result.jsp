<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="/css/styleResult.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="/js/ajaxResultTable.js"></script>
</head>
<body class="bg-dark text-white">
<noscript>
    JavaScript is off in your browser
</noscript>
<div class="container">

    <table class="table">
        <thead class="table-active">
        <tr class="table-dark">
            <th>Id</th>
            <th>Last name</th>
            <th>First name</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="table" class="table-light text-dark table-striped">
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
            <button class="btn btn-default" id="button${loop.count}"
                    onclick="changePageNumber(${ loop.count })">${ loop.count }</button>
        </c:forEach>
        </form>
    </c:if>

    <a href="/input" class="btn btn-info">Назад</a>
</div>

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