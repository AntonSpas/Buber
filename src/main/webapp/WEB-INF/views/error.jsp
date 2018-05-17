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
</head>
<body>

<c:url var="home" value="/"/>

<h3>
    <fmt:message key="${error}" />
</h3>
<p>
    <a href="${home}"><fmt:message key="home_path"/></a>
</p>
</body>
</html>
