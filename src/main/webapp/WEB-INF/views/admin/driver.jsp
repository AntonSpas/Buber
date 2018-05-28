<%@ include file="../fragment/head.jspf" %>
<fmt:message key="driver" var="title" />
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
                    <td>${driver.id}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="name"/></th>
                    <td>${driver.name}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="surname_table"/></th>
                    <td>${driver.surname}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="email_table"/></th>
                    <td>${driver.email}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="phone"/></th>
                    <td>${driver.phone}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="car_type"/></th>
                    <td>${driver.carType}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="car_model"/></th>
                    <td>${driver.carModel}</td>
                </tr>
                <tr class="table-new">
                    <th scope="row"><fmt:message key="car_number"/></th>
                    <td>${driver.carNumber}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>

<%@ include file="../fragment/footer.jspf" %>
