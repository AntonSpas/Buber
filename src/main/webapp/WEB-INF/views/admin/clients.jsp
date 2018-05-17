<%@ include file="../fragment/head.jspf" %>
<fmt:message key="clients" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="ban" value="/admin/ban-client"/>

<div id="h">
    <div class="container">
        <div class="centered">
            <h3>Clients</h3>
        </div>
        <div class="table-responsive">
            <table class="table table-sm table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="id"/></th>
                    <th scope="col"><fmt:message key="name"/></th>
                    <th scope="col"><fmt:message key="surname_table"/></th>
                    <th scope="col"><fmt:message key="email_table"/></th>
                    <th scope="col"><fmt:message key="phone"/></th>
                    <th scope="col"><fmt:message key="ban_scores"/></th>
                    <th scope="col"><fmt:message key="enabled"/></th>
                    <th scope="col"><fmt:message key="ban"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${clients}" var="client">
                    <tr>
                        <th scope="row">${client.id}</th>
                        <td>${client.name}</td>
                        <td>${client.surname}</td>
                        <td>${client.email}</td>
                        <td>${client.phone}</td>
                        <td>${client.banScores}</td>
                        <td>
                            <c:if test="${!client.enabled}">
                                <fmt:message key="banned"/>
                            </c:if>
                        </td>
                        <td><a href="${ban}?client_id=${client.id}" class="btn btn-next">
                            <fmt:message key="ban_client_btn"/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../fragment/simple_footer.jspf" %>
