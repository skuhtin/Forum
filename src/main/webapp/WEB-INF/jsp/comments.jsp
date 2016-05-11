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
<div class="headerComment">
    <h1>Read and discuss topic</h1>
    <form action="${forumPage}" method="get" >
        <button type="submit" class="btn btn-primary">Back to topic's page</button>
    </form>
</div>
<div class="myReadTopic">
    <div class="container">
        <h3>${topic.getHead()}</h3>
        ${topic.getText()}
    </div>
</div>

<div class="myComment">
    <div class="container">
        <h3>Comments:</h3>
        <div class="row">
            <c:forEach var="item" items="${comments}">
                <p><b>${item.getUserHandler()} said:</b>( <a href="${link}/${item.getUserHandler()}">Send msg</a> )<br>
                ${item.getText()}</p>
            </c:forEach>
        </div>
    </div>
</div>

<div class="myCommon">
    <div class="container">
        <form action="${topicPage}" method="post">
            <b>Add your comment:</b><br>
            <textarea name="comment" cols="40" rows="3"></textarea><br>
            <p><input type="submit" value="Send">
                <input type="reset" value="Clear"></p>
        </form>
    </div>
    <footer>
        <b>&copy; S.V.Kuhtin</b>
    </footer>
</div>

</body>
</html>
