<%@ include file="fragment/head.jspf" %>
<fmt:message key="login" var="title" />
<c:set var="simple" value="${true}" />
<%@ include file="fragment/header.jspf" %>

<c:url var="client_login" value="/client-login"/>
<c:url var="driver_login" value="/driver-login"/>
<fmt:message key="email" var="email" />

<main class="flex-fill">
<div id="t">
    <div class="container centered">
        <div class="row">
            <div class="col-12">
                <h3><fmt:message key="sign_in"/></h3>
                <br>
            </div>
            <div class="col-md-6">
                <h4><fmt:message key="client"/></h4>
                <p>
                    <a href="${client_login}" class="btn btn-next"><fmt:message key="client_login_btn"/></a>
                </p>
            </div>
            <div class="col-md-6">
                <h4><fmt:message key="driver"/></h4>
                <p>
                    <a href="${driver_login}" class="btn btn-next"><fmt:message key="driver_login_btn"/></a>
                </p>
            </div>
        </div>
    </div>
</div>
</main>

<%@ include file="fragment/footer.jspf" %>