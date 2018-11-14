
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Car Rent</title>
</head>

<jsp:include page="header.jsp"/>

<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="../img/5.jpg"
                 alt="First slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="../img/bmw-bmw-x72018-dzhip.jpg" alt="Second slide">
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="../img/lamborghini-urus-2018-topcar-vid-sboku.jpg" alt="Third slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1><fmt:message key="menu.name" bundle="${lang}"/></h1>
                    <p><fmt:message key="menu.text" bundle="${lang}"/></p>
                </div>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only"><fmt:message key="menu.previous" bundle="${lang}"/></span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only"><fmt:message key="menu.next" bundle="${lang}"/></span>
    </a>
</div>
<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/best_price.jpg" alt="Generic placeholder image" width="140"
                 height="140">
            <h2><fmt:message key="menu.value1" bundle="${lang}"/></h2>
            <p><fmt:message key="menu.value1.text" bundle="${lang}"/></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/2.jpg"
                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                 alt="Generic placeholder image" width="140" height="140">
            <h2><fmt:message key="menu.value2" bundle="${lang}"/></h2>
            <p><fmt:message key="menu.value2.text" bundle="${lang}"/>
            </p>
            <p><a class="btn btn-secondary" href="../img/x5-kh5-kh5m-x5m-bmw-x5-bmw-bmw-x5m.jpg" alt="" role="button"></a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/3.jpg" href="../img/x5-kh5-kh5m-x5m-bmw-x5-bmw-bmw-x5m.jpg"
                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                 alt="Generic placeholder image" width="140" height="140">
            <h2><fmt:message key="menu.value3" bundle="${lang}"/></h2>
            <p><fmt:message key="menu.value3.text" bundle="${lang}"/></p>
            <p><a class="btn btn-secondary" href="http://localhost:8080/carList" role="button"><fmt:message key="car.choose" bundle="${lang}"/></a></p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
</div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>