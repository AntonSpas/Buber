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
    <title><fmt:message key="error_page"/></title>
    <link rel="shortcut icon" href="/resources/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

<c:url var="home" value="/"/>

<div class="container">
    <h1 class="alert alert-danger centered"><fmt:message key="oops" /></h1>

    <br><br>
    <h3>
        <strong><fmt:message key="${error}" /></strong>
    </h3>

    <br><br>
    <h4><a class="link" href="${home}"><fmt:message key="home_path"/></a></h4>
</div>
</body>
</html>
