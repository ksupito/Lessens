<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Input</title>
    <link href="/css/bootstrap.css" rel="stylesheet">

</head>
<body class="bg-dark text-white">
<div class="container">
    <p class="text-center text-danger">${errorMessage}</p>
    <form method="post" action="/result">
        <div class="form-group row justify-content-center">
            <label class="control-label col-sm-2">Enter last name</label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="lastName" placeholder="Enter">
                <br>
                <input type="submit" class="btn btn-success" value="Find">
            </div>
        </div>
    </form>
</div>
</body>
</html>
