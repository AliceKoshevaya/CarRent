<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 28.10.2018
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>New order</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<p class="form-control">Make an order</p>
<div class="container">
        <div class="com-lg-1">
        <form method="POST" action="${pageContext.request.contextPath}/finalOrder">
            <div class="form-check">
                <c:out value="${errorMessage}"></c:out>
                <input class="form-check-input" type="radio" name="driver" id="exampleRadios1" value="true" checked>
                <label class="form-check-label" for="exampleRadios1">
                    With driver
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="driver" id="exampleRadios2" value="false">
                <label class="form-check-label" for="exampleRadios2">
                    Without driver
                </label>
            </div>
            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label">Start date</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="datetime-local" class="form-control" name="startRent" id="password"
                               placeholder="Enter rental start date"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm" class="cols-sm-2 control-label">End date</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="datetime-local" class="form-control" name="endRent" id="confirm"
                               placeholder="Enter rental end date"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="hidden" class="form-control" name="login" value="${sessionScope.user.id}" >
                    </div>
                </div>
            </div>
            <p class="cols-sm-2 control-label" style="color: darkred">Please enter the required passport information for the order</p>
            <div class="form-group">
                <label class="cols-sm-2 control-label">Third name</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="thirdName"  placeholder="Enter your third name">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="cols-sm-2 control-label">Passport Series</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="seria"  placeholder="Enter your passport series">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label  class="cols-sm-2 control-label">Passport Issued</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="issued" placeholder="Enter passport issued">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="cols-sm-2 control-label">Car</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="hidden" class="form-control" name="carId" value="${car.id}">
                        <table class="table table-lg">
                            <thead>
                            <tr class="table-primary">
                                <th scope="col">Characteristic</th>
                                <th scope="col">Value</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="table-primary">
                                <td>Car Name</td>
                                <td>${car.name}</td>
                            </tr>
                            <tr class="table-primary">
                                <td>State Number</td>
                                <td>${car.stateNumber}</td>
                            </tr>
                            <tr class="table-primary">
                                <td>Price:</td>
                                <td>${car.price}</td>
                            </tr>
                            <tr class="table-primary">
                                <td>Brand:</td>
                                <td>${car.brand.name}</td>
                            </tr>
                            <tr class="table-primary">
                                <td>Class:</td>
                                <td>${car.classCar.name}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="form-group ">
                <input type="submit" value="Accept" id="button" class="btn btn-primary btn-lg btn-block login-button />
                <a href="${pageContext.request.contextPath}/">
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>
