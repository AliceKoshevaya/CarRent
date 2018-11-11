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
    <link href="style.css" rel="stylesheet" type="text/css"/>
    <title>Cars</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<form method="GET" action="${pageContext.request.contextPath}/carList">
    <div class="col-lg-3 center-block">
        <label>Select Brand: </label>
        <select id="select1" name="selectBrand">
            <c:forEach items="${brands}" var="brand">
                <option value="${brand.id}">${brand.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-3 center-block">
        <label>Select Class Car: </label>
        <select id="select2" name="selectClass">
            <c:forEach items="${classes}" var="clazz">
                <option value="${clazz.id}">${clazz.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-lg-3 center-block">
        <select id="select3" name="selectSort">
            <option value="ASC:price">From cheap to expensive</option>
            <option value="DESC:price">From expensive to cheap</option>
            <option value="ASC:name">A-Z</option>
            <option value="DESC:name">Z-A</option>
        </select>
    </div>
    <div class="col-lg-3 center-block">
        <button type="submit" class="btn btn-default">Apply</button>
    </div>

</form>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Car name</th>
        <th scope="col">Price</th>
        <th scope="col">State number</th>
        <th scope="col">Brand</th>
        <th scope="col">Class</th>
    </tr>

    <c:forEach items="${cars}" var="car">
        <tr>
            <form method="POST" action="/makeOrder">
                <td name="name">${car.name}</td>
                <td name="price">${car.price}</td>
                <td name="stateNumber">${car.stateNumber}</td>
                <td name="brand">${car.brand.name}</td>
                <td name="class">${car.classCar.name}</td>
                <td><input type="hidden" name="carId" value="${car.id}"/><input type="submit" class="btn btn-default" value="Choose a car"/>
                </td>
            </form>
        </tr>
    </c:forEach>
    </thead>
</table>
</body>
</html>
