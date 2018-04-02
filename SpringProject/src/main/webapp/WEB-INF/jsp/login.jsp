<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Autorization</title>
    <spring:url value="/resources/css/bootstrap.css" var="stylecss"/>
    <link href="${stylecss}" rel="stylesheet"/>
</head>
<body class="bg-dark text-white">
<div class="container">
    <p class="text-center text-danger">${errorMessage}</p>
    <form method="post" action="/input">
        <div class="form-group row justify-content-center">
            <label class="control-label col-sm-1">Login</label>
            <div class="col-md-5">
                <input type="text" class="form-control" placeholder="Login" name="login" id="login">
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label class="control-label col-sm-1">Password</label>
            <div class="col-md-5">
                <input type="text" class="form-control" placeholder="Password" name="password" id="password">
                <br>
                <input type="submit" class="btn btn-success" value="Log in">
            </div>
        </div>
    </form>
</div>
</body>
</html>
