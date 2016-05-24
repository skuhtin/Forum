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
            <a href="${topicPage}">Back</a>
        </div>
    </div>
</nav>
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="head sidebar-brand">
                Banned users:
            </li>
            <c:forEach var="user" items="${users}">
                <li>
                    <a href="/admin/${user.getLogin()}">${user.getLogin()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="Msg row col-lg-12">
        <h1>User ${actionUser} is absent in our DB</h1>
    </div>
</div>
</body>
</html>