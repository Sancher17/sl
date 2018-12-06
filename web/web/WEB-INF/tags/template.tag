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
<a href="/user/getUsers">Пользователи системы</a><br>
<a href="/json/jsonTest">Json test</a><br>
<a href="/order/showOrders">Показать все ордера</a><br>

<br><br>
    <%--кнопка--%>
    <form action="/user/getUsers">
        <input type="submit" value="Показать юзеров" >
    </form>
<br><br>


<jsp:doBody/>



</body>

</html>