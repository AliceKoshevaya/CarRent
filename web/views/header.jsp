<%--
  User: ajiek
  Date: 25.10.2018
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/"> <fmt:message key="menu.home"
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
                        <c:when test=" ${sessionScope.user.id == 2}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/ordersList"><fmt:message
                                    key="menu.orders" bundle="${lang}"/></a>
                        </c:when>
                        <c:when test="${sessionScope.user.id == 1}">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/userList"><fmt:message
                                    key="menu.users" bundle="${lang}"/></a>
                        </c:when>
                    </c:choose>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/info"><fmt:message key="menu.info"
                                                                                                         bundle="${lang}"/></a>
                </div>
            </li>
        </ul>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login"><fmt:message key="menu.singIn"
                                                                                                     bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
        <div class="navbar-collapse collapse justify-content-between" id="navbarCollapse">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logOut"><fmt:message key="menu.singOut"
                                                                                                      bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
        <div class="collapse navbar-collapse justify-content" id="navbarCollase">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registration"><fmt:message
                            key="menu.register" bundle="${lang}"/></a>
                </li>
            </ul>
        </div>
        <p class="nav-item">Hello ${sessionScope.user.name}</p>
        <select class="selectpicker" name="lang" data-width="fit" onchange="location=this.value;">
            <%--<c:choose>--%>
                <%--<c:when test="${lang == 'ru'}">--%>
                    <%--<option value="http://localhost:8080/?lang=en">English</option>--%>
                    <%--<option value="http://localhost:8080/?lang=ru" selected="selected">Русский</option>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<option value="http://localhost:8080/?lang=en" selected="selected">English</option>--%>
                    <%--<option value="http://localhost:8080/?lang=ru">Русский</option>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
                <option value="" selected disabled hidden>Change language</option>
            <option value="http://localhost:8080/?lang=en">English</option>
            <option value="http://localhost:8080/?lang=ru">Русский</option>
        </select>
    </div>

</nav>

</div>
