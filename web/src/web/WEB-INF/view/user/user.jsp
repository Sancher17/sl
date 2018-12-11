<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:body>

<h1>Страница пользователей</h1>

    <%--First Name: ${userAttribute.firstName}--%>
    <%--<br>--%>
    <%--Last Name: ${userAttribute.lastName}--%>

<ul>
    <c:forEach var="temp" items="${userAttribute.toArray()}">
        <li> ${temp} </li>
    </c:forEach>
</ul>

    </jsp:body>


</page:template>
