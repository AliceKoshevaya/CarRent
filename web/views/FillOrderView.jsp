<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 28.10.2018
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>New order</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<p class="form-control"><fmt:message key="order.make" bundle="${lang}"/></p>
<div class="container">
        <div class="com-lg-1">
        <form method="POST" action="${pageContext.request.contextPath}/finalOrder">
            <div class="form-check">
                <c:out value="${errorMessage}"></c:out>
                <input class="form-check-input" type="radio" name="driver" id="exampleRadios1" value="true" checked>
                <label class="form-check-label" for="exampleRadios1">
                    <fmt:message key="order.with.driver" bundle="${lang}"/>
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="driver" id="exampleRadios2" value="false">
                <label class="form-check-label" for="exampleRadios2">
                    <fmt:message key="order.without.driver" bundle="${lang}"/>
                </label>
            </div>
            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label"><fmt:message key="order.start" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="datetime-local" class="form-control" name="startRent" id="password"
                               placeholder="<fmt:message key="order.enter.start" bundle="${lang}"/>" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm" class="cols-sm-2 control-label"><fmt:message key="order.end" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="datetime-local" class="form-control" name="endRent" id="confirm"
                               placeholder="<fmt:message key="order.enter.end" bundle="${lang}"/>" />
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
            <p class="cols-sm-2 control-label" style="color: darkred"><fmt:message key="order.additional.info" bundle="${lang}"/></p>
            <div class="form-group">
                <label class="cols-sm-2 control-label"><fmt:message key="order.third" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="thirdName"  placeholder="<fmt:message key="order.enter.third" bundle="${lang}"/>" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="cols-sm-2 control-label"><fmt:message key="order.seria" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="seria"  placeholder=<fmt:message key="order.enter.seria" bundle="${lang}"/>>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label  class="cols-sm-2 control-label"><fmt:message key="order.data" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="issued" placeholder="<fmt:message key="order.enter.data" bundle="${lang}"/>" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="cols-sm-2 control-label"><fmt:message key="order.car" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="hidden" class="form-control" name="carId" value="${car.id}">
                        <table class="table table-lg">
                            <thead>
                            <tr class="table-primary">
                                <th scope="col"><fmt:message key="order.characteristic" bundle="${lang}"/></th>
                                <th scope="col"><fmt:message key="order.value" bundle="${lang}"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="table-primary">
                                <td><fmt:message key="car.name" bundle="${lang}"/></td>
                                <td>${car.name}</td>
                            </tr>
                            <tr class="table-primary">
                                <td><fmt:message key="car.number" bundle="${lang}"/></td>
                                <td>${car.stateNumber}</td>
                            </tr>
                            <tr class="table-primary">
                                <td><fmt:message key="car.price2" bundle="${lang}"/></td>
                                <td>${car.price}</td>
                            </tr>
                            <tr class="table-primary">
                                <td><fmt:message key="car.brand2" bundle="${lang}"/></td>
                                <td>${car.brand.name}</td>
                            </tr>
                            <tr class="table-primary">
                                <td><fmt:message key="car.class2" bundle="${lang}"/></td>
                                <td>${car.classCar.name}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="form-group ">
                <input type="submit" value="<fmt:message key="order.accept" bundle="${lang}"/>" id="button" class="btn btn-primary btn-lg btn-block login-button" />
                <a href="${pageContext.request.contextPath}/">
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>
