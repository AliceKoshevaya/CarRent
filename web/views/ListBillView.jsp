<%@include file="../views/jspf/include.jspf" %>
<html>
<head>
    <title><fmt:message key="menu.all.bill" bundle="${lang}"/></title>
</head>
<jsp:include page="header.jsp"/>
<body>
<table>
    <tr>
        <th><fmt:message key="bill.id" bundle="${lang}"/></th>
        <th><fmt:message key="bill.type" bundle="${lang}"/></th>
        <th><fmt:message key="bill.status" bundle="${lang}"/></th>
        <th><fmt:message key="car.price" bundle="${lang}"/></th>
        <th><fmt:message key="bill.date" bundle="${lang}"/></th>
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
