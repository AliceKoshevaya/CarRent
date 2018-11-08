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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>List orders</title>
</head>
<jsp:include page="header2.jsp"/>
<body>
<table class="table">
    <thead class="thead-dark">
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
                            <input type="hidden" name="newStatus" value="IN_PROGRESS"/>
                            <input type="submit" class="btn btn-default" value="Confirm"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CLOSED"/>
                            <input type="submit" class="btn btn-default" value="Reject"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'CRASH'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CRASH"/>
                            <input type="submit" class="btn btn-default" value="Set bill"/>
                        </form>
                    </td>
                </c:when>
                <c:when test="${order.status == 'IN_PROGRESS'}">
                    <td>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CRASH"/>
                            <input type="submit" class="btn btn-default" value="Crash"/>
                        </form>
                        <form method="POST" action="/confirm">
                            <input type="hidden" name="idOrder" value="${order.id}"/>
                            <input type="hidden" name="status" value="${order.status}"/>
                            <input type="hidden" name="newStatus" value="CLOSED"/>
                            <input type="submit" class="btn btn-default" value="Returned"/>
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
    </thead>
</table>
</body>
</html>
