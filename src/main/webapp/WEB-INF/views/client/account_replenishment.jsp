<%@ include file="../fragment/head.jspf" %>
<fmt:message key="account_replenishment" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="replenish_account" value="/client/replenish-account"/>

<main class="flex-fill">
<div id="smf">
    <form action = "${replenish_account}" method = "POST" class="needs-validation" novalidate>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4>${title}</h4>
            <label for="amount"><fmt:message key="amount"/></label>
            <input type="number" step="0.01" size="4" class="form-control" id="amount"
                   name="amount" pattern="[0-9]*(\.[0-9]*)?" min="0"
                   required placeholder=<fmt:message key="amount"/>>
            <div class="invalid-feedback">
                <fmt:message key="invalid_money_amount"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="replenish"/></button>
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
</main>

<%@ include file="../fragment/footer.jspf" %>
