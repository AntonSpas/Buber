<%@ include file="../fragment/head.jspf" %>
<fmt:message key="administration" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="car_registration" value="/admin/register-car"/>
<c:url var="orders" value="/admin/orders"/>
<c:url var="clients" value="/admin/clients"/>

<div id="t">
    <div class="container centered">
        <div class="row">
            <div class="col-12">
                <h3><fmt:message key="options"/></h3>
                <br>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="driver_registration"/></h4>
                <p>
                    <a href="${car_registration}" class="btn btn-next">
                        <fmt:message key="driver_registration_btn"/></a>
                </p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="orders"/></h4>
                <p>
                    <a href="${orders}" class="btn btn-next"><fmt:message key="orders_btn"/></a>
                </p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="clients"/></h4>
                <p>
                    <a href="${clients}" class="btn btn-next"><fmt:message key="clients_btn"/></a>
                </p>
            </div>
        </div>
    </div>
</div>

<%@ include file="../fragment/simple_footer.jspf" %>
