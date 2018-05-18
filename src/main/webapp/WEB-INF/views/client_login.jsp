<%@ include file="fragment/head.jspf" %>
<fmt:message key="client_login" var="title" />
<c:set var="simple" value="${true}" />
<%@ include file="fragment/header.jspf" %>

<c:url var="client_login" value="/client-login"/>
<fmt:message key="email" var="email" />

<main class="flex-fill">
<div class="page-wrap">
    <div id="smf">
        <form action = "${client_login}" method = "POST" class="needs-validation" novalidate>
            <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
                <h4><fmt:message key="sign_in"/></h4>
                <label for="email">${email}</label>
                <input type="email" class="form-control" id="email" name="email"
                       pattern="[\w\.-]+@[\w\.-]+\.[a-zA-Z]{2,3}"
                       placeholder="${email}" required>
                <div class="invalid-feedback">
                    <fmt:message key="invalid_email"/>
                </div>
            </div>
            <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
                <label for="password"><fmt:message key="password"/></label>
                <input type="password" class="form-control" id="password" name="password"
                       pattern="(?=.*[a-z])(?=.*\d)[0-9a-zA-Z]{6,16}"
                       placeholder=<fmt:message key="password"/> required>
                <div class="invalid-feedback">
                    <fmt:message key="invalid_password"/>
                </div>
            </div>
            <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
                <button class="btn btn-submit" type="submit"><fmt:message key="sign-in"/></button>
            </div>
        </form>
        <c:if test="${!isValid}">
            <div class="error">
                <div class="container">
                        ${message}
                </div>
            </div>
        </c:if>
    </div>
</div>
</main>

<%@ include file="fragment/footer.jspf" %>
