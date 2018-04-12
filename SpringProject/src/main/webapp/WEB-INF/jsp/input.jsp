<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Input</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<div class="container">
    <p class="text-center text-danger">${errorMessage}</p>
    <form:form method="post" modelAttribute="lastName" action="/result">
        <div class="form-group row justify-content-center">
            <form:label path="lastName" class="control-label col-sm-2">Enter last name</form:label>
            <div class="col-md-5">
                <form:input path="lastName" class="form-control" placeholder="Enter"/>
                <form:errors path="lastName" class="text-danger"/>
                <br>
                <input type="submit" class="btn btn-success" value="Find">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
