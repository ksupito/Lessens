<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>error</title>
    <spring:url value="/resources/css/bootstrap.css" var="stylecss"/>
    <link href="${stylecss}" rel="stylesheet"/>
</head>
<body>
<h1 class="bg-danger text-center"><spring:message code="errors.error"/></h1>
</body>
</html>
