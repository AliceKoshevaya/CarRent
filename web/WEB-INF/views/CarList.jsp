<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 25.10.2018
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Cars</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/carList">
<table>
    <tr>
        <th>Id_car</th>
        <th>Car_name</th>
        <th>Price</th>
        <th>State_number</th>
        <th>Brand</th>
        <th>Class</th>
    </tr>

    <c:forEach items="${cars}" var="car">
    <tr>
        <td>${car.id}</td>
        <td>${car.carName}</td>
        <td>${car.price}</td>
        <td>${car.stateNumber}</td>
        <td>${car.brand.name}</td>
        <td>${car.classCar.name}</td>
    </tr>
    </c:forEach>

</table>
</body>
</html>
