<%@tag description="orders" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language :
not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
<%@attribute name="orders" required="true" type="java.util.List"%>
<%@attribute name="driver_ref" required="true" %>
<%@attribute name="client_ref" required="true" %>

<div class="table-responsive">
    <table class="table table-sm table-hover">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="order_id"/></th>
            <th scope="col"><fmt:message key="start_point"/></th>
            <th scope="col"><fmt:message key="end_point"/></th>
            <th scope="col"><fmt:message key="cost"/></th>
            <th scope="col"><fmt:message key="status"/></th>
            <th scope="col"><fmt:message key="driver"/></th>
            <th scope="col"><fmt:message key="client"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <t:orderTableRow order="${order}" driver_ref="${driver_ref}"
                             client_ref="${client_ref}"/>
        </c:forEach>
        </tbody>
    </table>
</div>