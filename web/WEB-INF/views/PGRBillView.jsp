<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Thank you for ordering, here is your account.</h3>
<table>
    <tr>
        <th>Id_bill</th>
        <th>Type</th>
        <th>Status</th>
        <th>Sum</th>
        <th>Date</th>
        <th>Id_order</th>
    </tr>
    <tr>
        <form method="POST" action="/bill">
            <input type="hidden" name="txt1" value="${bill.id}"/>
            <td>${bill.id}</td>
            <td>${bill.type}</td>
            <td>${bill.status}</td>
            <td>${bill.sum}</td>
            <td>${bill.date}</td>
            <td>${bill.order.id}</td>
            <c:choose>
                <c:when test="${bill.status == false}">
                    <td><input type="submit"  name = "pay"  value="Pay"/></td>
                </c:when>
                <c:when test="${bill.status == true}">
                    <b>The bill is paid</b>
                </c:when>
            </c:choose>
        </form>
    </tr>
</table>
</body>
</html>
