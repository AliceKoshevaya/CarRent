<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 25.10.2018
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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
                    <h1>Car Rental</h1>
                    <p>For a business and busy person who has come to another country with a working trip, it is inconceivable to be left without a car. He will be dependent on external factors, he will not be able to precisely manage his time. And time is money. We offer such people to contact our company.
                        We provide rental cars in Kharkov, as well as throughout the country.</p>
                </div>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/best_price.jpg" alt="Generic placeholder image" width="140"
                 height="140">
            <h2>Reasonable prices</h2>
            <p>Thanks to favorable rates, our customers save on car rental without sacrificing comfort and safety. Low
                prices for car rental allow individuals to move freely throughout the country, not owning a personal
                car, and businesses - to optimize the cost of maintaining a fleet.</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/2.jpg"
                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                 alt="Generic placeholder image" width="140" height="140">
            <h2>Convenient service</h2>
            <p>In the company you can order a specific car for a specific time without paperwork and bail. Booking is a
                100% guarantee that at the right time the ordered car will be delivered directly to your doors: on time,
                with a clean interior and a full tank.
            </p>
            <p><a class="btn btn-secondary" href="../img/x5-kh5-kh5m-x5m-bmw-x5-bmw-bmw-x5m.jpg" alt="" role="button"></a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="rounded-circle" src="../img/3.jpg" href="../img/x5-kh5-kh5m-x5m-bmw-x5-bmw-bmw-x5m.jpg"
                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                 alt="Generic placeholder image" width="140" height="140">
            <h2>New cars</h2>
            <p>All cars in our fleet are modern models of 2015–2017. release. They are in excellent technical condition,
                equipped with GPS navigation and Wi-Fi, fully insured (CASCO, OSAGO, driver’s life insurance)</p>
            <p><a class="btn btn-secondary" href="http://localhost:8080/carList" role="button">Choose a car</a></p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
</div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>