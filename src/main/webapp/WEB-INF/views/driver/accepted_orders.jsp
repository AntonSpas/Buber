<%@ include file="../fragment/head.jspf" %>
<fmt:message key="accepted_orders" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="home" value="/"/>
<c:url var="confirm" value="/driver/confirm"/>
<c:url var="absence" value="/driver/absence"/>


<main class="flex-fill">
    <div id="h">
        <div class="container">
            <div class="centered">
                <h3>Orders</h3>
            </div>
            <div class="table-responsive">
                <table class="table table-sm table-hover">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="order_id"/></th>
                        <th scope="col"><fmt:message key="start_point"/></th>
                        <th scope="col"><fmt:message key="end_point"/></th>
                        <th scope="col"><fmt:message key="cost"/></th>
                        <th scope="col"><fmt:message key="confirm"/></th>
                        <th scope="col"><fmt:message key="client_absence"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${unconfirmed_orders}" var="order">
                        <tr>
                            <th scope="row">${order.id}</th>
                            <td><fmt:message key="${order.street}" /></td>
                            <td><fmt:message key="${order.destinationStreet}" /></td>
                            <td>${order.cost}</td>
                            <td><a href="${confirm}?id=${order.id}" class="btn btn-choose">
                                <fmt:message key="confirm_order_btn" /></a></td>
                            <td><a href="${absence}?id=${order.id}" class="btn btn-reject">
                                <fmt:message key="client_absence_btn" /></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<%@ include file="../fragment/footer.jspf" %>
