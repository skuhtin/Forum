<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About me</title>
    <link rel="stylesheet" type="text/css" href="/FORUM/css/style.css">
    <link rel="stylesheet" href="/FORUM/css/bootstrap.css">
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="text-lg-center">
            <h1>Sergiy Kuhtin</h1>
            <h4>sergiy.kuhtin@gmail.com</h4>
        </div>
        <div>
            <h4>Projects:</h4>
            <ul>
                <li>
                    <a href="https://github.com/skuhtin/LRUCashe">LRU Cache</a>
                </li>
                <li>
                    <a href="https://github.com/skuhtin/Forum">Forum</a>
                </li>
                <li>
                    <a href="https://github.com/skuhtin/KPI ">Key Performance Indicator</a>
                </li>
                <li>
                    <a href="https://github.com/skuhtin/shorturl">ShortUrl</a>
                </li>
            </ul>
        </div>
        <div>
            <h4>Summary of qualifications:</h4>
            <ul>
                <li>
                    Proven experience with Java EE and related technologies.
                </li>
                <li>
                    Deep knowledge and understanding of OOD/OOP/Design Patterns.
                </li>
                <li>
                    Good understanding of network protocols and storage systems including RDBMS,
                    NoSQL.
                </li>
                <li>
                    Hands-on experience in web-application development utilizing Java Servlets,
                    Tomcat/Jetty, JDBC, Hibernate.
                </li>
                <li>
                    Talented problem solver able to think out of the box.
                </li>
            </ul>
        </div>
        <div class="text-lg-center">
            <form action="/FORUM/login" method="get" role="form">
                <button type="submit" class="btn btn-primary">Back to login page</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
