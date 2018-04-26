<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Autorization</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body class="bg-dark text-white">
<div class="container">
    <spring:message code="login.language"/> : <a href="?lang=en">English</a> | <a href="?lang=ru">Руссий</a>
    <c:if test="${errorMessage}">
        <p class="text-center text-danger"><spring:message code="login.error.message"/></p>
    </c:if>
    <c:if test="${logout}">
        <p class="text-center text-warning"><spring:message code="login.logout.message"/></p>
    </c:if>
    <form name='loginForm' method="post" action="${loginUrl}">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
        <div class="form-group row justify-content-center my-sm-3">
            <label class="control-label col-sm-1"><spring:message code="login.login.form"/></label>
            <div class="col-md-5">
                <input type="text" class="form-control" placeholder='<spring:message code="login.placeholder.login"/>'
                       name="login" id="login">
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label class="control-label col-sm-1"><spring:message code="login.password.form"/></label>
            <div class="col-md-5">
                <input type="text" class="form-control"
                       placeholder='<spring:message code="login.placeholder.password"/>' name="password" id="password">
                <br>
                <spring:message code="login.button.logIn" var="logIn"></spring:message>
                <input type="submit" class="btn btn-success" value="${logIn}">
            </div>
        </div>
    </form>
</div>
</body>
</html>