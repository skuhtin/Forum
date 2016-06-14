<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic list</title>
    <link rel="stylesheet" type="text/css" href="/FORUM/css/style.css">
    <link rel="stylesheet" href="/FORUM/css/simple-sidebar.css">
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
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="head sidebar-brand">
                Banned users:
            </li>
            <c:forEach var="user" items="${users}">
                <li>
                    <a href="/FORUM/admin/${user.getLogin()}">${user.getLogin()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->
    <!-- Page Content -->
    <div class="jumbotron">
        <div class="text-lg-center">
            <h1>What do you think about user ${actionUser}?</h1>
            <p>
            <form action="/FORUM/admin/${actionUser}" method="post">
                <select name="action" size="1"
                        style="background-color: wheat; border-color: wheat">
                    <option value="${actionBun}">${actionBun}</option>
                    <option value="${actionUnBun}">${actionUnBun}</option>
                    <option value="${actionKick}">${actionKick}</option>
                </select>
                <button type="submit"
                        style="background-color: wheat; border-bottom-color: wheat">Select
                    action
                </button>
            </form>
            </p>
        </div>
    </div>
</div>
</body>
</html>