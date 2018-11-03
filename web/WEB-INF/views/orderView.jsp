<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 28.10.2018
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>New order</title>
</head>
<body>
<p class="form-control">Make an order</p>
<div class="container">
    <div class="row main-form">
        <form method="POST" action="${pageContext.request.contextPath}/finalOrder">
            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Driver or not</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="driver" placeholder="true/false">
                    </div>
                </div>
            </div>
            <%--<form action="">--%>
                <%--<input type="radio" name="driver" value="with"> With a driver<br>--%>
                <%--<input type="radio" name="driver" value="without"> Without driver<br>--%>
            <%--</form>--%>

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
                <label for="name" class="cols-sm-2 control-label">User</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="login" id="name" placeholder="Enter your name">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Car</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="hidden" class="form-control" name="carId" value="${car.id}">
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
<form>
    <div style="text-align: right; vertical-align: top">
        <p>Car Name</p>
        <p>${car.carName}</p>
        <p>Price</p>
        <p>${car.price}
        </p>
        <p>StateNumber</p>
        <p>${car.stateNumber}
        </p>
        <p>Brand</p>
        <p>${car.brand.name}
        </p>
        <p>Class</p>
        <p>${car.classCar.name}
        </p>
    </div>
</form>
</body>
</html>
