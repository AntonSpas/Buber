<%@ include file="../fragment/head.jspf" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:message key="orders" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="driver" value="/admin/driver-info"/>
<c:url var="client" value="/admin/client-info"/>
<c:url var="orders_page" value="/admin/orders"/>

<div id="h">
    <div class="container">
        <div class="centered">
            <h3><fmt:message key="orders"/></h3>
        </div>

        <t:ordersTable orders="${orders}" driver_ref="${driver}"
                         client_ref="${client}"/>


        <%--<div class="table-responsive">
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

                    <t:orderTableRow order="${order}" driver_ref="${driver}"
                                     client_ref="${client}"/>

                </c:forEach>
                </tbody>
            </table>
        </div>--%>

        <div class="d-flex justify-content-center">
            <nav aria-label="Pagination">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item">
                            <a class="page-link" href="${orders_page}?page=${currentPage - 1}">
                                <fmt:message key="previous"/></a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pagesQuantity}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active">
                                    <a class="page-link" href="#">${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="${orders_page}?page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt pagesQuantity}">
                        <li class="page-item">
                            <a class="page-link" href="${orders_page}?page=${currentPage + 1}">
                                <fmt:message key="next"/></a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>

<%@ include file="../fragment/unbounded_footer.jspf" %>
