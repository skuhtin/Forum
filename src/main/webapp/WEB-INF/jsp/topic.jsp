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
            <a href="/user/${userName}">Hi, ${userName}, you have ${countNewMessage} new
                messages</a>
        </div>
        <div class="navbar-form navbar-right">
            <a href="${loginPage}">LogOut</a>
        </div>
    </div>
</nav>
<div class="header">
    <h1>List of topics</h1>
</div>
<div class="myCenter">
    <div class="container">
        <div class="row">
            <ul>
                <c:forEach var="item" items="${topics}">
                    <li>
                        <h4><a href="/forum/${item.getKey()}">${item.getValue().getHead()}</a></h4>
                        added by ${item.getValue().getHandleUser()}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="myCommon">
<div class="container">
    <form action="${forumPage}" method="post">
        <p><h3>Add new topic:</h3></p>
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
