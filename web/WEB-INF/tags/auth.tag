<%@ tag body-content="empty" %>
<%@ attribute name="userName" rtexprvalue="true" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="WebAppResources" var="lang"/>

<c:choose>
    <c:when test="${empty userName}">
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login"><fmt:message key="menu.singIn"
                                                                                                     bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollase">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registration"><fmt:message
                            key="menu.register" bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
    </c:when>
    <c:when test="${not empty userName}">
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <p class="navbar-brand"><fmt:message key="menu.hello" bundle="${lang}"/> ${userName}</p>
                </li>
            </ul>
        </div>
        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logOut"><fmt:message key="menu.singOut" bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
    </c:when>
</c:choose>