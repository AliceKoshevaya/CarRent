<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 05.11.2018
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
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
    <div class="form-group ">
        <label><fmt:message key="select.brand" bundle="${lang}"/></label>
        <select id="select1" name="selectBrand">
            <c:forEach items="${brands}" var="brand">
                <option value="${brand.id}">${brand.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group ">
        <label><fmt:message key="select.class" bundle="${lang}"/></label>
        <select id="select2" name="selectClass">
            <c:forEach items="${classes}" var="clazz">
                <option value="${clazz.id}">${clazz.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group ">
        <select id="select3" name="selectSort">
            <option value="ASC:price"><fmt:message key="select.asc" bundle="${lang}"/></option>
            <option value="DESC:price"><fmt:message key="select.desc" bundle="${lang}"/></option>
            <option value="ASC:name"><fmt:message key="select.asc.name" bundle="${lang}"/></option>
            <option value="DESC:name"><fmt:message key="select.desc.name" bundle="${lang}"/></option>
        </select>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-default"><fmt:message key="car.apply" bundle="${lang}"/></button>
    </div>

</form>
<table>
    <tr>
        <th><fmt:message key="car.name" bundle="${lang}"/></th>
        <th><fmt:message key="car.price" bundle="${lang}"/></th>
        <th><fmt:message key="car.status" bundle="${lang}"/></th>
        <th><fmt:message key="car.number" bundle="${lang}"/></th>
        <th><fmt:message key="car.brand" bundle="${lang}"/></th>
        <th><fmt:message key="car.class" bundle="${lang}"/></th>
    </tr>

    <c:forEach items="${cars}" var="car">
        <tr>
            <td name="name">${car.name}</td>
            <td name="price">${car.price}</td>
            <td name="status">${car.status}</td>
            <td name="stateNumber">${car.stateNumber}</td>
            <td name="brand">${car.brand.name}</td>
            <td name="class">${car.classCar.name}</td>
            <td>
                <form method="get" action="/editCar"><input type="hidden" name="carId" value="${car.id}"/>
                    <input type="submit" class="btn btn-default" value="<fmt:message key="car.edit" bundle="${lang}"/>" /></form>
            </td>
            <td>
                <form method="post" action="/deleteCar"><input type="hidden" name="carId" value="${car.id}"/>
                    <input type="submit" class="btn btn-default" value="<fmt:message key="car.delete" bundle="${lang}"/>" /></form>
            </td>
        </tr>
    </c:forEach>

    <form method="POST" action="${pageContext.request.contextPath}/addCar">
        <input style="float: left;" type="text" name="CarName" placeholder="Enter car name"/>
        <input style="float: left;" type="text" name="Price" placeholder="Enter price"/>
        <input style="float: left;" type="text" name="StateNumber" placeholder="Enter state Number"/>
        <input style="float: left;" type="text" name="Brand" placeholder="Enter brand id"/>
        <input style="float: left;" type="text" name="Class" placeholder="Enter class id"/>
        <input style="float: left;" type="submit" class="btn btn-default" value="<fmt:message key="car.add" bundle="${lang}"/>" />
    </form>
</table>
</body>
</html>
