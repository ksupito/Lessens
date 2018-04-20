<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Input</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<div class="container">
    <c:if test="${errorMessage=='noCoincidencesError'}">
        <p class="text-center text-danger"><spring:message code="input.noCoincidences.error"/></p>
    </c:if>
    <form:form method="post" modelAttribute="lastName" action="/result">
        <div class="form-group row justify-content-center my-sm-3">
            <form:label path="lastName" class="control-label col-sm-2"><spring:message code="input.form"/></form:label>
            <div class="col-md-5">
                <spring:message code="input.placeholder.form" var="placeholder"></spring:message>
                <form:input path="lastName" class="form-control" placeholder="${placeholder}"/>
                <form:errors path="lastName" class="text-danger"/>
                <br>
                <spring:message code="input.button" var="find"></spring:message>
                <input type="submit" class="btn btn-success" value="${find}">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
