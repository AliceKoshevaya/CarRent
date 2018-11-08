<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 04.11.2018
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All bills</title>
</head>
<jsp:include page="header2.jsp"/>
<body>
<table>
    <tr>
        <th>Id_bill</th>
        <th>Type</th>
        <th>Status</th>
        <th>Price</th>
        <th>Date</th>
    </tr>

    <c:forEach items="${bills}" var="bill">
        <tr>
            <form method="get" action="/billList">
                <td>${bill.id}</td>
                <td>${bill.status}</td>
                <td>${bill.type}</td>
                <td>${bill.sum}</td>
                <td>${bill.date}</td>
            </form>
        </tr>
    </c:forEach>

</table>

</body>
</html>
