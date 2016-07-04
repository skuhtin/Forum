<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authentication form</title>
    <link rel="stylesheet" type="text/css" href="/FORUM/css/style.css">
    <link rel="stylesheet" href="/FORUM/css/bootstrap.css">

</head>
<body>

<nav class="navbar navbar-dark bg-inverse navbar-fixed-top">
    <div class="container">
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <form action="/FORUM/ban" method="get" role="form">
                    <button type="submit" class="btn btn-primary">Press for
                        registration &raquo;</button>
                </form>
            </li>
            <li class="nav-item form-inline pull-lg-right">
                <form action="/FORUM/login" method="post" role="form">
                    <div class="form-group">
                        <input type="text" name="login" placeholder="Login" class="form-control">
                        <input type="password" name="password" placeholder="Password"
                               class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <div class="text-lg-center">
            <h1>Welcome to our forum!</h1>

            <h3 class="text-danger">login/pas : admin/admin - for test admin's privilege</h3>

            <form action="/FORUM/aboutMe" method="get" role="form">
                <button type="submit" class="btn btn-primary">About me</button>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h2>Some news 1</h2>

            <p>Text of news</p>
        </div>
        <div class="col-md-4">
            <h2>Some news 2</h2>

            <p>Text of news</p>
        </div>
        <div class="col-md-4">
            <h2>Some news 3</h2>

            <p>Text of news</p>
        </div>
    </div>

    <hr>
    <footer>
        <p>&copy; S.V.Kuhtin</p>
    </footer>

</div>

</body>
</html>