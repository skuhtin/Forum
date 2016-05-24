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
<div class="Msg">
  <h2>Message has been sent successfully!</h2>
  <form action="${returnLink}" method="get" >
    <button type="submit" class="btn btn-primary">Back to comment's page</button>
  </form><br>
</div>
</body>
</html>