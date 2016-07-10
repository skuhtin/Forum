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
    <div class="row ">
        <div class="container text-lg-center">
            <h2>Read and discuss topic</h2>
            <form action="${forumPage}" method="get">
                <button type="submit" class="btn btn-primary">Back to topic's page</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <h3>${topic.getHead()}</h3>
            <p>${topic.getText()}</p>
        </div>
    </div>

    <div class="row">
        <div class="container">
            <h3>Comments:</h3>
            <c:forEach var="item" items="${comments}">
                <div class="card card-block">
                    <b>${item.getUserHandler()} said:</b>( <a
                            href="${link}${item.getUserHandler()}">Send
                        msg</a> )<br>
                            ${item.getText()}
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="row">
        <div class="container">
            <div class="card card-block">
                <form action="${topicPage}" method="post">
                    <div>
                        <h6>Add your comment:</h6>
                        <textarea name="comment" required class="form-control" cols="40" rows="3"></textarea><br>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-primary" value="Send">
                        <input type="reset" class="btn btn-default" value="Clear">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <footer>
        <div class="center-block">
            <b>&copy; S.V.Kuhtin</b>
        </div>
    </footer>
</div>

</body>
</html>
