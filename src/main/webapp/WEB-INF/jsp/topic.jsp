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
        <h1>List of topics</h1>
        <c:if test="${userIsAdmin}">
            <form action="/FORUM/admin/" method="get">
                <div class="container">
                    <input type="text" name="actionUser" rows="1" cols="10" required placeholder="User Name">
                    <button type="submit" class="btn btn-danger">User properties</button>
                </div>
            </form>
        </c:if>
        <c:if test="${!userIsAdmin}">
            <form action="/FORUM/message/admin" method="get">
                <div class="container">
                    <button type="submit" class="btn btn-danger">Send message to admin</button>
                </div>
            </form>
        </c:if>
    </div>


    <div class="container">
        <div class="row">
            <ul>
                <c:forEach var="item" items="${topics}">
                    <li class="card card-block">
                        <a href="/FORUM/forum/${item.getKey()}">${item.getValue().getHead()} </a>
                        - added by ${item.getValue().getHandleUser()}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="container">
        <form action="${forumPage}" method="post">
            <p>

            <h3>Add new topic:</h3></p>
            <b>Head of topic:</b><br>
            <textarea name="head" required cols="40" rows="1"></textarea><br>
            <b>Text of topic:</b><br>
            <textarea name="comment" cols="40" rows="3"></textarea><br>

            <p><input type="submit" value="Send">
                <input type="reset" value="Cancel"></p>
        </form>
    </div>
    <footer>
        <b>&copy; S.V.Kuhtin</b>
    </footer>
</div>
</body>
</html>
