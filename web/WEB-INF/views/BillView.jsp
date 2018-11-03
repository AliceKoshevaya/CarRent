<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Bill</title>
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
            <td><input type="submit"  name = "pay"  value="Pay"/></td>
        </form>
    </tr>
</table>

</body>
</html>
