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
    <!-- /#sidebar-wrapper -->
    <!-- Page Content -->
    <div class="Msg">
        <div class="row">
            <div class="col-lg-12">
                <h1>What do you think about user ${actionUser}?</h1>

                <p>

                <form action="/admin/${actionUser}" method="post">
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
</div>
</body>
</html>