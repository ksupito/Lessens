<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="/css/styleResult.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/ajaxResultTable.js"></script>

</head>
<body class="bg-dark text-white">

<noscript>
    JavaScript is off in your browser
</noscript>

<div class="container">
    <table class="table text-center">
        <thead class="table-active">
        <tr class="table-dark">
            <th class="text-center">Id</th>
            <th class="text-center">Last name</th>
            <th class="text-center">First name</th>
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
                    <button class="btn btn-default" data-toggle="modal" data-target="#myModal" id="buttonShowMore"
                            onclick="showMore(this)">
                        <i class="glyphicon glyphicon-info-sign"></i>
                        Show More
                    </button>
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
    <div id="loader" class="loader">
        <p><img src="/img/tenor.gif"></p>
    </div>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">X</button>
            </div>
            <div class="modal-body text-dark">
                <span id="showResult"></span><br>
            </div>
        </div>
    </div>
</div>

</body>
</html>