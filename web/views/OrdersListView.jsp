<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../views/fspf/include.jspf" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="menu.list.orders" bundle="${lang}"/></title>
</head>
<jsp:include page="header.jsp"/>
<body>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th><fmt:message key="order.id" bundle="${lang}"/></th>
        <th><fmt:message key="order.driver" bundle="${lang}"/></th>
        <th><fmt:message key="car.status" bundle="${lang}"/></th>
        <th><fmt:message key="order.start.rent" bundle="${lang}"/></th>
        <th><fmt:message key="order.end.rent" bundle="${lang}"/></th>
        <th><fmt:message key="order.id.user" bundle="${lang}"/></th>
        <th><fmt:message key="order.id.car" bundle="${lang}"/></th>
    </tr>

    <c:forEach items="${orders}" var="order">
        <tr>

            <td name="idOrder" scope="col">${order.id}</td>
            <td scope="col">${order.driver}</td>
            <td scope="col">${order.status}</td>
            <td scope="col">${order.startRent}</td>
            <td scope="col">${order.endRent}</td>
            <td scope="col">${order.user.id}</td>
            <td scope="col">${order.car.id}</td>
                <%--<form method="POST" action="/confirm"><input type="hidden" name="idOrder" value="${order.id}"/>--%>
            <c:choose>
                <c:when test="${order.status == 'NEW'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="<fmt:message key="order.status.progress" bundle="${lang}"/>"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="order.status.confirm" bundle="${lang}"/>"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="<fmt:message key="order.status.closed" bundle="${lang}"/>"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="order.status.reject" bundle="${lang}"/>"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'CRASH'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="<fmt:message key="order.status.crash" bundle="${lang}"/>"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="order.set.bill" bundle="${lang}"/>"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'IN_PROGRESS'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="<fmt:message key="order.status.crash" bundle="${lang}"/>"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="order.crash" bundle="${lang}"/>"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="<fmt:message key="order.status.closed" bundle="${lang}"/>"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="order.status.returned" bundle="${lang}"/>"/>
                        </form>
                    </td>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
            <td><a href="/billList?idOrder=${order.id}"><fmt:message key="order.bill" bundle="${lang}"/></a></td>
                <%--</form>--%>
        </tr>
    </c:forEach>
    </thead>
</table>
</body>
</html>
