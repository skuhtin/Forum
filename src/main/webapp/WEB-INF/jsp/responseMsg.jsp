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
<div>
    <nav class="navbar navbar-dark bg-inverse navbar-fixed-top">
        <div class="container">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a href="/FORUM/user/${userName}">${userName}'s page (${countNewMessage} new
                        messages)</a>
                </li>
                <li class="nav-item pull-lg-right">
                    <a href="${loginPage}">LogOut</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="text-lg-center">
            <h2>Message has been sent successfully!</h2>

            <form action="${returnLink}" method="get">
                <button type="submit" class="btn btn-primary">Back to comment's page</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>