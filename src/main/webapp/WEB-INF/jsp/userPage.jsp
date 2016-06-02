<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic list</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="/css/simple-sidebar.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-dark bg-inverse navbar-fixed-top">
    <div class="container">
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a href="/user/${userName}">${userName}'s page (${countNewMessage} new
                    messages)</a>
            </li>
            <li class="nav-item pull-lg-right">
                <a href="${loginPage}">LogOut</a>
            </li>
        </ul>
    </div>
</nav>
<!-- Page Content -->
<div class="jumbotron">
    <div class="text-lg-center">
        <h1>${userName}, your private messages.</h1>

        <form action="/forum" method="get">
            <button type="submit" class="btn btn-primary">Back to main page</button>
        </form>
    </div>
    <div class="container">
        <div class="row">
            <ul>
                <c:forEach var="message" items="${messages}">
                    <li class="card card-block">
                        <b>${message.getFromUser()} wrote: </b><br>
                            ${message.getText()}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>