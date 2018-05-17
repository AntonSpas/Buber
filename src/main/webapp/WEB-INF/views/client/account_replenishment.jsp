<%@ include file="../fragment/head.jspf" %>
<fmt:message key="account_replenishment" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="replenish_account" value="/client/replenish-account"/>

<div id="smf">
    <form action = "${replenish_account}" method = "POST">
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4>${title}</h4>
            <label for="amount"><fmt:message key="amount"/></label>
            <input type="number" step="0.01" size="4" class="form-control" id="amount" name="amount"
                   required placeholder=<fmt:message key="amount"/>>
            <div class="invalid-feedback">
                Please enter money.
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="replenish"/></button>
        </div>
    </form>
</div>

<%@ include file="../fragment/simple_footer.jspf" %>
