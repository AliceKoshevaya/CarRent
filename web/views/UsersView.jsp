<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 05.11.2018
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="menu.all.users" bundle="${lang}"/></title>
</head>
<jsp:include page="header.jsp"/>
<body>
    <table class="table table-lg">
        <thead>
        <tr class="table-primary">
            <th scope="col"><fmt:message key="order.characteristic" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="order.value" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <form method="get" action="${pageContext.request.contextPath}/userList">
        <c:forEach items="${users}" var="user">
            <tr class="table-primary">
                <td><fmt:message key="user.id" bundle="${lang}"/></td>
                <td name="idUser">${user.id}</td>
            </tr>
            <tr class="table-primary">
                <td><fmt:message key="user.name" bundle="${lang}"/></td>
                <td>${user.name}</td>
            </tr>
            <tr class="table-primary">
                <td><fmt:message key="user.lastName" bundle="${lang}"/></td>
                <td>${user.lastName}</td>
            </tr>
            <tr class="table-primary">
                <td><fmt:message key="user.login" bundle="${lang}"/></td>
                <td>${user.login}</td>
            </tr>
            <tr class="table-primary">
                <td><fmt:message key="user.role" bundle="${lang}"/></td>
                <td>${user.role.name}</td>
            </tr>
            <tr class="table-primary">
                <td><fmt:message key="user.block" bundle="${lang}"/></td>
                <td>${user.block}</td>
            </tr>
            </form>
            <tr class="table-primary">
                <c:choose>
                <c:when test="${user.role.name != 'manager'}">
                    <td><form id="makeManager" method="post" action="/makeManager"><input type="hidden" name="idUser" value="${user.id}"/>
                        <input type="submit" value="<fmt:message key="user.manager" bundle="${lang}"/>" /></form></td>
                </c:when>
                <c:when test="${user.role.name == 'manager'}">
                    <td></td>
                </c:when>
                </c:choose>
                <form method="post" action="/block"><input type="hidden" name="idUser" value="${user.id}"/>
                <c:choose>
                    <c:when test="${user.block == false}">
                        <td><input type="submit" value="<fmt:message key="menu.block" bundle="${lang}"/>" /></td>
                    </c:when>
                    <c:when test="${user.block == true}">
                        <td><input type="submit" value="<fmt:message key="menu.unblock" bundle="${lang}"/>" /></td>
                    </c:when>
                </c:choose>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
