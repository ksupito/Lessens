<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Autorization</title>
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
