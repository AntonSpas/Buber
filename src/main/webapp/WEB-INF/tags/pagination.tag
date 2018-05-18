<%@tag description="pagination" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language :
not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization" />
<%@attribute name="currentPage" required="true" %>
<%@attribute name="orders_page" required="true" %>
<%@attribute name="pagesQuantity" required="true" %>

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
