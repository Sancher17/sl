<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student form</title>
</head>
<body>

<form:form action="processForm" modelAttribute="student">

    Firs name: <form:input path="first_name"/>
    <br><br>
    Last name: <form:input path="last_name"/>

    <input type="submit" value="Submit" />

</form:form>



</body>
</html>
