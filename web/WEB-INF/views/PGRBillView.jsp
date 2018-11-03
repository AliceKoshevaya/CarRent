<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/bill" method="post" name="frm">
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
            <input type="hidden" name="txt1" value="${bill.id}"/>
                <td>${bill.id}</td>
                <td>${bill.type}</td>
                <td>${bill.status}</td>
                <td>${bill.sum}</td>
                <td>${bill.date}</td>
                <td>${bill.order.id}</td>
                <td><input type="submit" name="pay" value="Pay" /></td>
        </tr>
    </table>
</body>
</html>
