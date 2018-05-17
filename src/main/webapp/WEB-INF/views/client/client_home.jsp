<%@ include file="../fragment/head.jspf" %>
<fmt:message key="client_home" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="ride_order" value="/client/ride-order"/>
<c:url var="replenish_account" value="/client/replenish-account"/>

<div id="t">
    <div class="container centered">
        <div class="row">
            <div class="col-12">
                <h3><fmt:message key="options"/></h3>
                <br>
            </div>
            <div class="col-md-6">
                <h4><fmt:message key="ride_legend"/></h4>
                <p>
                    <a href="${ride_order}" class="btn btn-next"><fmt:message key="ride_btn"/></a>
                </p>
            </div>
            <div class="col-md-6">
                <h4><fmt:message key="payment_legend"/></h4>
                <p>
                    <a href="${replenish_account}" class="btn btn-next"><fmt:message key="replenish_btn"/></a>
                </p>
            </div>
        </div>
    </div>
    <c:if test="${negative_account}">
        <div class="error">
            <div class="container">
                <fmt:message key="negative_account"/>
            </div>
        </div>
    </c:if>
</div>

<%@ include file="../fragment/simple_footer.jspf" %>
