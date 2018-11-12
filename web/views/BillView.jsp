<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 03.11.2018
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<h3><fmt:message key="bill.finish" bundle="${lang}"/></h3>
<table>
    <tr>
        <th><fmt:message key="bill.id" bundle="${lang}"/></th>
        <th><fmt:message key="bill.type" bundle="${lang}"/></th>
        <th><fmt:message key="bill.status" bundle="${lang}"/></th>
        <th><fmt:message key="bill.sum" bundle="${lang}"/></th>
        <th><fmt:message key="bill.date" bundle="${lang}"/></th>
        <th><fmt:message key="order.id" bundle="${lang}"/></th>
    </tr>
    <tr>
        <form method="POST" action="/bill">
            <input type="hidden" name="idBill" value="${bill.id}"/>
            <td>${bill.id}</td>
            <td>${bill.type}</td>
            <td>${bill.status}</td>
            <td>${bill.sum}</td>
            <td>${bill.date}</td>
            <td>${bill.order.id}</td>
            <c:choose>
                <c:when test="${bill.status == false}">
                    <td><input type="submit"  name = "pay"  value=<fmt:message key="bill.pay" bundle="${lang}"/>/></td>
                </c:when>
                <c:when test="${bill.status == true}">
                    <b><fmt:message key="bill.payed" bundle="${lang}"/></b>
                </c:when>
            </c:choose>
        </form>
    </tr>
</table>
</body>
</html>
