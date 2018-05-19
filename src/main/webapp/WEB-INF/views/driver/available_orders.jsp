<%@ include file="../fragment/head.jspf" %>
<fmt:message key="available_orders" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="home" value="/"/>
<c:url var="accept" value="/driver/chosen-order"/>
<c:url var="unconfirmed" value="/driver/accepted-orders"/>

<main class="flex-fill">
<div id="h">
    <div class="container">
        <div class="centered">
            <h3><fmt:message key="orders"/></h3>
        </div>
        <div class="table-responsive">
            <table class="table table-sm table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="start_point"/></th>
                    <th scope="col"><fmt:message key="end_point"/></th>
                    <th scope="col"><fmt:message key="cost"/></th>
                    <th scope="col"><fmt:message key="accept"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td><fmt:message key="${order.street}" /></td>
                        <td><fmt:message key="${order.destinationStreet}" /></td>
                        <td>${order.cost}</td>
                        <td><a href="${accept}?id=${order.id}" class="btn btn-choose">
                            <fmt:message key="accept_order_btn"/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${unconfirmed_present}">
            <a href="${unconfirmed}" class="btn btn-reject"><fmt:message key="unconfirmed_orders_btn" /></a>
        </c:if>
    </div>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
