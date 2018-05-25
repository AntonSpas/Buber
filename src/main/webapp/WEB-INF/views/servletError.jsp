<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>Error Page</title>
    <link rel="shortcut icon" href="/resources/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
          crossorigin="anonymous">
</head>
<body>

<c:url var="home" value="/"/>

<div class="container-fluid">
    <h1 align=center class="alert alert-danger">oops, error!</h1>

    <h3 align=center>Unfortunately an unexpected error has occurred. Below you can find the error details.</h3>

    <h2>Details</h2>
    <ul>
        <c:if test="${not empty statusCode}">
            <li><strong>Status Code: </strong>${statusCode}</li>
        </c:if>
        <c:if test="${not empty exceptionName}">
            <li><strong>Exception: </strong>${exceptionName}</li>
        </c:if>
        <c:if test="${not empty message}">
            <li><strong>Exception Message: </strong>${message}</li>
        </c:if>
        <c:if test="${not empty requsetUri}">
            <li><strong>Requested URI: </strong>${requestUri}</li>
        </c:if>
    </ul>

</div>

<p>
    <a href="${home}"><fmt:message key="home_path"/></a>
</p>
</body>
</html>
