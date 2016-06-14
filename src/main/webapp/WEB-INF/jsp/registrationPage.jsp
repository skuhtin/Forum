<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic list</title>
    <link rel="stylesheet" type="text/css" href="/FORUM/css/style.css">
    <link rel="stylesheet" href="/FORUM/css/bootstrap.css">
</head>
<body>

<div class="form-signin">
    <h3 class="form-signin-heading">Registration form:</h3>

    <form action="/FORUM/ban" method="post">
        <div class="form-group">
            <input type="text" name="login" placeholder="Enter your login"
                   class="form-control">
            <input type="password" name="password" placeholder="Enter your password"
                   class="form-control">
            <button type="submit" name="send" class="btn btn-block btn-primary">Send</button>
            <a class="btn btn-block btn-danger" href="/FORUM/login" role="button">Back to
                registration page</a>
        </div>
    </form>
</div>

</body>
</html>
