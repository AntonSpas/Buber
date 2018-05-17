<%@tag description="order info" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language :
not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
<%@attribute name="order" required="true" type="by.epam.buber.model.RideOrder" %>
<%@attribute name="driver_ref" required="true" %>
<%@attribute name="client_ref" required="true" %>

<tr>
    <th scope="row">${order.id}</th>
    <td><fmt:message key="${order.street}" /></td>
    <td><fmt:message key="${order.destinationStreet}" /></td>
    <td>${order.cost}</td>
    <td><fmt:message key="${order.orderStatus}" /></td>
    <td><a href="${driver_ref}?driver_id=${order.driverId}" class="btn btn-next">
        <fmt:message key="driver"/></a></td>
    <td><a href="${client_ref}?client_id=${order.clientId}" class="btn btn-choose">
        <fmt:message key="client"/></a></td>
</tr>

