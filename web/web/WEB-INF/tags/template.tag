<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>CAFE</title>
</head>

<body>
<h1>Программа управления кафе</h1><br>

<a href="/">Главная страница</a><br>
<a href="student/showForm">Student form</a><br>
<a href="/student/getUser">Пользователи системы</a><br>
<a href="/json/jsonTest">Json test</a><br>


<jsp:doBody/>



</body>

</html>