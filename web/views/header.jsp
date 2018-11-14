
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../views/jspf/include.jspf" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><fmt:message key="menu.name" bundle="${lang}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/"><fmt:message key="menu.home"
                                                                                            bundle="${lang}"/><span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/carList"> <fmt:message key="menu.cars"
                                                                                                    bundle="${lang}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userInfo"><fmt:message key="menu.acc"
                                                                                                    bundle="${lang}"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="menu.drop" bundle="${lang}"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <c:choose>
                        <c:when test="${sessionScope.user.role.name == 'manager'}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/ordersList"><fmt:message
                                    key="menu.orders" bundle="${lang}"/></a>
                        </c:when>
                        <c:when test="${sessionScope.user.role.name == 'administrator'}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/userList"><fmt:message
                                    key="menu.users" bundle="${lang}"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/info"><fmt:message key="menu.info"
                                                                                                                 bundle="${lang}"/></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
            <select class="selectpicker" name="lang" data-width="fit" onchange="location=this.value;">
                <option value="" selected disabled hidden><fmt:message key="menu.change.language"
                                                                       bundle="${lang}"/></option>
                <ln:localeReceiver servletContext="${pageContext.servletContext}"/>
            </select>
        </ul>
        <my:auth userName="${sessionScope.user.name}"/>
    </div>

</nav>

</div>
