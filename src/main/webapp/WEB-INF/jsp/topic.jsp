<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic list</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-form navbar-left">
            <a href="/user/${userName}">${userName}'s page (${countNewMessage} new
                messages)</a>
        </div>
        <div class="navbar-form navbar-right">
            <a href="${loginPage}">LogOut</a>
        </div>
    </div>
</nav>
<div class="header">
    <h1 class="text-center">List of topics</h1>
    <c:if test="${userIsAdmin}">
        <form action="/admin/" method="get">
            <div class="container">
                <input type="text" name="actionUser" rows="1" cols="10" placeholder="User Name">
                <button type="submit" class="btn btn-danger">User properties</button>
            </div>
        </form>
    </c:if>
    <c:if test="${!userIsAdmin}">
        <form action="/message/admin" method="get">
            <div class="container">
                <button type="submit" class="btn btn-danger">Send message to admin</button>
            </div>
        </form>
    </c:if>
</div>
<div class="myCenter">
    <div class="container">
        <div class="row">
            <ul>
                <c:forEach var="item" items="${topics}">
                    <li>
                        <a href="/forum/${item.getKey()}">${item.getValue().getHead()} </a>
                        - added by ${item.getValue().getHandleUser()}<br>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="myCommon">
    <div class="container">
        <form action="${forumPage}" method="post">
            <p>

            <h3>Add new topic:</h3></p>
            <b>Head of topic:</b><br>
            <textarea name="head" cols="40" rows="1"></textarea><br>
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
