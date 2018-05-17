<%@ include file="../fragment/head.jspf" %>
<fmt:message key="ride" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="ride_order" value="/client/ride-order"/>
<fmt:message key="ride_order" var="ride" />
<fmt:message key="departure" var="departure" />
<fmt:message key="destination" var="destination" />

<div class="container ot">
    <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2">
        <div class="centered">
            <h3><fmt:message key="your_order" /></h3>
        </div>
        <table class="table table-borderless">
            <tbody>
            <tr class="table-new">
                <th scope="row"><fmt:message key="start_point" /></th>
                <td><fmt:message key="${order.street}" /></td>
            </tr>
            <tr class="table-new">
                <th scope="row"><fmt:message key="end_point" /></th>
                <td><fmt:message key="${order.destinationStreet}" /></td>
            </tr>
            <tr class="table-new">
                <th scope="row"><fmt:message key="cost" /></th>
                <td>${order.cost}</td>
            </tr>
            <tr class="table-new">
                <th scope="row"><fmt:message key="status" /></th>
                <td><fmt:message key="${order.orderStatus}" /></td>
            </tr>
            <tr class="table-new">
                <th scope="row"><fmt:message key="car_type" /></th>
                <td><fmt:message key="${order.carType}" /></td>
            </tr>
            <c:if test="${not empty driver}">
                <tr class="table-new">
                    <th scope="row"><fmt:message key="driver" /></th>
                    <td>${driver.name} ${driver.surname}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="car_model" /></th>
                    <td>${driver.carModel}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="car_number" /></th>
                    <td>${driver.carNumber}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="phone" /></th>
                    <td>${driver.phone}</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="../fragment/simple_footer.jspf" %>
