<%@ include file="../fragment/head.jspf" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:message key="orders" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="driver" value="/admin/driver-info"/>
<c:url var="client" value="/admin/client-info"/>
<c:url var="orders_page" value="/admin/orders"/>

<main class="flex-fill">
<div id="h">
    <div class="container">
        <div class="centered">
            <h3><fmt:message key="orders"/></h3>
        </div>

        <t:ordersTable orders="${orders}" driver_ref="${driver}"
                         client_ref="${client}"/>

        <t:pagination currentPage="${currentPage}" orders_page="${orders_page}"
                      pagesQuantity="${pagesQuantity}"/>
    </div>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
