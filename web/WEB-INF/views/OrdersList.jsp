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
            <form method="POST" action="/ordersList">
                <td>${order.id}</td>
                <td>${order.driver}</td>
                <td>${order.status}</td>
                <td>${order.startRent}</td>
                <td>${order.endRent}</td>
                <td>${order.user.id}</td>
                <td>${order.car.id}</td>
                <td><input type="submit" value="Edit"/></td>
                <td><input type="submit" value="Bills"/></td>
            </form>
        </tr>
    </c:forEach>

</table>
</body>
</html>
