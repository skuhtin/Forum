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
<nav class="navbar navbar-dark bg-inverse navbar-fixed-top">
  <div class="container">
    <ul class="nav navbar-nav">
      <li class="nav-item">
        <a href="/user/${userName}">${userName}'s page (${countNewMessage} new
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
  <h3>Send private message to ${actionUser}</h3>
  <form action="${returnLink}" method="get" >
    <button type="submit" class="btn btn-primary">Back to comment's page</button>
  </form><br>
  <form action="${link}" method="post">
    <textarea name="message" cols="40" rows="3"></textarea>
    <p><input type="submit" value="Send">
    <input type="reset" value="Clear"></p>
  </form>
  </div>
</div>
</body>
</html>