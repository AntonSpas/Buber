<%@ include file="../fragment/head.jspf" %>
<fmt:message key="client" var="title" />
<%@ include file="../fragment/header.jspf" %>

<main class="flex-fill">
    <div class="container ot">
        <div class="col-lg-6 offset-lg-3 col-md-8 offset-md-2">
            <div class="centered">
                <h3>${title}</h3>
            </div>
            <table class="table table-borderless">
                <tbody>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="id"/></th>
                    <td>${client.id}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="name"/></th>
                    <td>${client.name}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="surname_table"/></th>
                    <td>${client.surname}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="email_table"/></th>
                    <td>${client.email}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="phone"/></th>
                    <td>${client.phone}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="ban_scores"/></th>
                    <td>${client.banScores}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="enabled"/></th>
                    <td>
                        <c:if test="${!client.enabled}">
                            <fmt:message key="banned"/>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@ include file="../fragment/footer.jspf" %>
