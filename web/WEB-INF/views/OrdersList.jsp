<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List orders</title>
</head>
<body>
<table>
    <tr>
        <th>Id_order</th>
        <th>Driver</th>
        <th>Status</th>
        <th>Start_rent</th>
        <th>End_rent</th>
        <th>Id_user</th>
        <th>Id_car</th>
    </tr>

    <c:forEach items="${orders}" var="order">
        <tr>

            <td name="idOrder">${order.id}</td>
            <td>${order.driver}</td>
            <td>${order.status}</td>
            <td>${order.startRent}</td>
            <td>${order.endRent}</td>
            <td>${order.user.id}</td>
            <td>${order.car.id}</td>
                <%--<form method="POST" action="/confirm"><input type="hidden" name="idOrder" value="${order.id}"/>--%>
            <c:choose>
                <c:when test="${order.status == 'NEW'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="IN_PROGRESS"/>
                            <input type="submit" value="Confirm"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CLOSED"/>
                            <input type="submit" value="Reject"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'CRASH'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CRASH"/>
                            <input type="submit" value="Set bill"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'IN_PROGRESS'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CRASH"/>
                            <input type="submit" value="Crash"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CLOSED"/>
                            <input type="submit" value="Returned"/>
                        </form>
                    </td>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
            <td><a href="/billList?idOrder=${order.id}">bills</a></td>
                <%--</form>--%>
        </tr>
    </c:forEach>

</table>
</body>
</html>
