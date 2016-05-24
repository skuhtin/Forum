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
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-form navbar-right">
            <a href="${loginPage}">LogOut</a>
        </div>
        <div class="navbar-form navbar-left">
            <a href="${forumPage}">Back</a>
        </div>
    </div>
</nav>
<!-- Page Content -->
<div class="Msg">
    <div class="row">
        <div class="col-lg-12">
            <h1>${userName}, your private messages.</h1>

            <form action="/forum" method="get">
                <button type="submit" class="btn btn-primary">Back to main page</button>
            </form>
            <ul class="text-left">
                <c:forEach var="message" items="${messages}">
                    <li><p>
                        <b>${message.getFromUser()} wrote: </b><br>
                            ${message.getText()}
                    </p></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

</body>
</html>