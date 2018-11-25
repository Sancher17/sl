<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User form</title>
    <style>
        .error{color:red}
    </style>
</head>
<body>
<h2> Введите логин и пароль</h2>
<i>Fill out the form. Asterisk (*) means required.</i>
<br><br>

<form:form action="processForm" modelAttribute="user">

    Логин(*): <form:input path="login"/>

    <br><br>
    Пароль(*): <form:input path="password"/>
    <form:errors path="password" cssClass="error"/>
    <br><br>

    <input type="submit" value="Ввести">

</form:form>


</body>
</html>
