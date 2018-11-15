<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 25.10.2018
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
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
        <label><fmt:message key="select.brand" bundle="${lang}"/></label>
        <select id="select1" name="selectBrand">
            <option value="all">All</option>
            <c:forEach items="${brands}" var="brand">
                <option value="${brand.id}">${brand.name}</option>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById('name').value = "<?php echo $_GET['select1'];?>";
        </script>
    </div>
    <div class="col-lg-3 center-block">
        <label><fmt:message key="select.class" bundle="${lang}"/></label>
        <select id="select2" name="selectClass">
            <option value="all">All</option>
            <c:forEach items="${classes}" var="clazz">
                <option value="${clazz.id}">${clazz.name}</option>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById('name').value = "<?php echo $_GET['select2'];?>";
        </script>
    </div>
    <div class="col-lg-3 center-block">
        <select id="select3" name="selectSort">
            <option value="ASC:price"><fmt:message key="select.asc" bundle="${lang}"/></option>
            <option value="DESC:price"><fmt:message key="select.desc" bundle="${lang}"/></option>
            <%--<option value="ASC:name"><fmt:message key="asc.name" bundle="${lang}"/></option>--%>
            <%--<option value="DESC:name"><fmt:message key="desc.name" bundle="${lang}"/></option>--%>
        </select>
        <script type="text/javascript">
            document.getElementById('name').value = "<?php echo $_GET['select3'];?>";
        </script>
    </div>
    <div class="col-lg-3 center-block">
        <button type="submit" class="btn btn-default"><fmt:message key="car.apply" bundle="${lang}"/></button>
    </div>

</form>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col"><fmt:message key="car.name" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="car.price" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="car.number" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="car.brand" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="car.class" bundle="${lang}"/></th>
    </tr>

    <c:forEach items="${cars}" var="car">
        <tr>
            <form method="POST" action="/makeOrder">
                <td name="name">${car.name}</td>
                <td name="price">${car.price}</td>
                <td name="stateNumber">${car.stateNumber}</td>
                <td name="brand">${car.brand.name}</td>
                <td name="class">${car.classCar.name}</td>
                <td><input type="hidden" name="carId" value="${car.id}"/><input type="submit" class="btn btn-default" value=<fmt:message key="car.choose" bundle="${lang}"/>
                </td>
            </form>
        </tr>
    </c:forEach>
    </thead>
</table>
</body>
</html>
