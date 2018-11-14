<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 25.10.2018
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<form method="POST" action="${pageContext.request.contextPath}/userInfo">
    <c:choose>
    <c:when test="${empty sessionScope.user }">
        <h1><fmt:message key="acc.acc" bundle="${lang}"/></h1>
    <br />
    </c:when>
        <c:when test="${not empty sessionScope.user}">
        <h3><fmt:message key="menu.hello" bundle="${lang}"/> ${sessionScope.user.name}</h3>
        <br>
            <fmt:message key="user.name" bundle="${lang}"/> <b>${sessionScope.user.name}</b>
        <br>
            <fmt:message key="user.lastName" bundle="${lang}"/> <b>${sessionScope.user.lastName } </b>
        <br>
            <fmt:message key="user.login" bundle="${lang}"/> <b>${sessionScope.user.login}</b>
        <br>
        </c:when>
    </c:choose>
    <jsp:include page="footer.jsp"/>

</body>
</html>