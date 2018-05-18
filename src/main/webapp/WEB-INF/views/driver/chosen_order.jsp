<%@ include file="../fragment/head.jspf" %>
<fmt:message key="chosen_order" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="confirm" value="/driver/confirm"/>
<c:url var="absence" value="/driver/absence"/>

<fmt:message key="ride_order" var="ride" />
<fmt:message key="departure" var="departure" />
<fmt:message key="destination" var="destination" />

<main class="flex-fill">
<div class="container wb">
    <div class="container">
        <h4><fmt:message key="order_details" /></h4><br>
        <fmt:message key="start_point" />: <fmt:message key="${order.street}" /><br>
        <fmt:message key="end_point" />: <fmt:message key="${order.destinationStreet}" /><br>
        <fmt:message key="cost" />: ${order.cost}<br>
        <br>
        <h3><fmt:message key="client_details" /></h3><br>
        <fmt:message key="first_name" />: ${client.name}<br>
        <fmt:message key="surname" />: ${client.surname}<br>
        <fmt:message key="phone" />: ${client.phone}<br>
        <br>
        <a href="${confirm}" class="btn btn-choose"><fmt:message key="confirm_order_btn" /></a>
        <a href="${absence}" class="btn btn-reject"><fmt:message key="client_absence_btn" /></a>
    </div>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
