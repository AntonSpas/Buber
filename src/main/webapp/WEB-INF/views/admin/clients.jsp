<%@ include file="../fragment/head.jspf" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:message key="clients" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="ban" value="/admin/ban-client"/>

<main class="flex-fill">
<div id="h">
    <div class="container">
        <div class="centered">
            <h3><fmt:message key="clients"/></h3>
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

        <t:pagination currentPage="${currentPage}" orders_page="${orders_page}"
                      pagesQuantity="${pagesQuantity}"/>
    </div>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
