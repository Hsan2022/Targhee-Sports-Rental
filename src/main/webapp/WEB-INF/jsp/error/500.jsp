<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="../include/header.jsp" />

<h1 class="page-title">This is a 500 server error page</h1>


<%-- Restrict content within to users with 'ADMIN' authority--%>
<sec:authorize access="hasAnyAuthority('ADMIN')">

    <%--will show if any of these 5 conditions are not empty--%>
    <div style="margin-left:30px;" class="pb-5">
        <br><br>
        <c:if test="${not empty requestUrl}">
            <p>${requestUrl}</p>
        </c:if>
        <h3>Stack Trace</h3>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
        <c:if test="${not empty stackTrace}">
            <p>${stackTrace}</p>
        </c:if>
        <c:if test="${not empty rootCause}">
            <h3>Root Cause</h3>
            <p>${rootCause}</p>
        </c:if>
        <c:if test="${not empty rootTrace}">
            <p>${rootTrace}</p>
        </c:if>
    </div>

</sec:authorize>


<jsp:include page="../include/footer.jsp" />