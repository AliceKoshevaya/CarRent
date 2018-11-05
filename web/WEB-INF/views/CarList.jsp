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
<body>
<form method="GET" action="${pageContext.request.contextPath}/carList">
    <div class="form-group ">
        <label>Select Brand: </label>
        <select id="select1" name="selectBrand">
            <c:forEach items="${brands}" var="brand">
                <option value="${brand.id}">${brand.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group ">
        <label>Select Class Car: </label>
        <select id="select2" name="selectClass">
            <c:forEach items="${classes}" var="clazz">
                <option value="${clazz.id}">${clazz.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group ">
        <select id="select3" name="selectSort">
            <option value="ASC:price">From cheap to expensive</option>
            <option value="DESC:price">From expensive to cheap</option>
            <option value="ASC:name">A-Z</option>
            <option value="DESC:name">Z-A</option>
        </select>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-default">Apply</button>
    </div>

</form>
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
            <form method="POST" action="/makeOrder">
                <td name = "carName">${car.carName}</td>
                <td name = "price">${car.price}</td>
                <td name = "stateNumber">${car.stateNumber}</td>
                <td name = "brand">${car.brand.name}</td>
                <td name = "class">${car.classCar.name}</td>
                <td><input type="hidden" name="carId" value="${car.id}"/><input type="submit" value="Choose a car"/></td>
                <td><input type="submit" value="Edit"/></td>
                <td><form method="post" action="/deleteCar"><input type="hidden" name="carId" value="${car.id}"/>
                    <input type="submit" value="Delete"/></form></td>
            </form>
        </tr>
    </c:forEach>
    <input type="submit" value="Add a car"/>
</table>
</body>
</html>
