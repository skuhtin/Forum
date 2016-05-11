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
<div class="Msg">
  <h3>Send private message to ${toUser}</h3>
  <form action="${returnLink}" method="get" >
    <button type="submit" class="btn btn-primary">Back to comment's page</button>
  </form><br>
  <form action="${link}" method="post">
    <textarea name="message" cols="40" rows="3"></textarea>
    <p><input type="submit" value="Send">
    <input type="reset" value="Clear"></p>
  </form>
</div>
</body>
</html>