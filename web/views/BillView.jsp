
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<h3><fmt:message key="bill.finish" bundle="${lang}"/></h3>
<table class="table table-sm">
    <tr>
        <th class="bg-success"><fmt:message key="bill.id" bundle="${lang}"/></th>
        <th class="bg-success"><fmt:message key="bill.type" bundle="${lang}"/></th>
        <th class="bg-success"><fmt:message key="bill.status" bundle="${lang}"/></th>
        <th class="bg-success"><fmt:message key="bill.sum" bundle="${lang}"/></th>
        <th class="bg-success"><fmt:message key="bill.date" bundle="${lang}"/></th>
    </tr>
    <tr>
        <form method="POST" action="/bill">
            <input type="hidden" name="idBill" value="${bill.id}"/>
            <td>${bill.id}</td>
            <td>${bill.type}</td>
            <td>${bill.status}</td>
            <td>${bill.sum}</td>
            <td>${bill.date}</td>
            <c:choose>
                <c:when test="${bill.status == false}">
                    <td><input type="submit" class="btn btn-default" name="pay" value="<fmt:message key="bill.pay" bundle="${lang}"/>" /></td>
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
