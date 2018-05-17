<%@ include file="fragment/head.jspf" %>
<fmt:message key="sign_up" var="title" />
<c:set var="simple" value="${true}" />
<%@ include file="fragment/header.jspf" %>

<c:url var="sign_up" value="/sign-up"/>
<fmt:message key="first_name" var="first_name" />
<fmt:message key="surname" var="surname" />
<fmt:message key="email" var="email" />
<fmt:message key="create_password" var="create_password" />
<fmt:message key="phone_number" var="phone_number" />

<div id="spf">
    <form action = "${sign_up}" method = "POST" class="needs-validation" novalidate>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4><fmt:message key="sign_up"/></h4>
            <label for="name">${first_name}</label>
            <input type="text" class="form-control" id="name" name="name"
                   pattern="([\u0400-\u04FF]|[A-Za-z]){2,20}"
                   placeholder="${first_name}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_name"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="surname">${surname}</label>
            <input type="text" class="form-control" id="surname" name="surname"
                   pattern="([\u0400-\u04FF]|[A-Za-z]){2,20}"
                   placeholder="${surname}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_surname"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
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
                   placeholder="${create_password}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_password"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="phone"><fmt:message key="phone"/></label>
            <input type="tel" class="form-control" id="phone" name="phone"
                   pattern="\+375\((29|33|44|25)\)\d{3}-\d{2}-\d{2}"
                   placeholder="${phone_number}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_phone"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="sign-up"/></button>
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

<%@ include file="fragment/unbounded_footer.jspf" %>

