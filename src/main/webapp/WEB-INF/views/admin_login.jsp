<%@ include file="fragment/head.jspf" %>
<fmt:message key="admin_login" var="title" />
<c:set var="simple" value="${true}" />
<%@ include file="fragment/header.jspf" %>

<c:url var="admin_login" value="/admin-login"/>

<div id="smf">
    <form action = "${admin_login}" method = "POST" class="needs-validation" novalidate>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4><fmt:message key="sign_in"/></h4>
            <label for="login"><fmt:message key="login"/></label>
            <input type="text" class="form-control" id="login" name="login"
                   required placeholder=<fmt:message key="login"/>>
            <div class="invalid-feedback">
                <fmt:message key="invalid_login"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="password"><fmt:message key="password"/></label>
            <input type="password" class="form-control" id="password" name="password"
                   required placeholder=<fmt:message key="password"/>>
            <div class="invalid-feedback">
                <fmt:message key="invalid_password"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="forward"/></button>
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

<%@ include file="fragment/simple_footer.jspf" %>
