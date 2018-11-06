<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 05.11.2018
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit a car</title>
</head>
<body>
<p class="form-control">Edit a car</p>
<div class="container">
    <div class="row main-form">
        <form method="POST" action="${pageContext.request.contextPath}/editCar">
            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Car Name</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="idCar" value="${car.carName}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="cols-sm-2 control-label">Price</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="price" value="${car.price}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="cols-sm-2 control-label">State Number</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="stateNumber" value="${car.stateNumber}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Brand</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="brand" id="name" value="${car.brand.name}">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="username" class="cols-sm-2 control-label">Class</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="class" id="username"
                               value="${car.classCar.name}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="username" class="cols-sm-2 control-label">Status</label>
                <div class="cols-sm-10">
                    <select id="select1" name="status" value="${car.status}">
                        <c:forEach items="${statuses}" var="status">
                            <c:choose>
                                <c:when test="${car.status.equals(status)}">
                                    <option value="${status}" selected="selected">${status}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status}">${status}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group ">
                <input type="submit" value="Edit" id="button" class="btn btn-primary btn-lg btn-block login-button />
                <a href="${pageContext.request.contextPath}/">
            </div>
        </form>
    </div>
</div>
</body>
</html>
