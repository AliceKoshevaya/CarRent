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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Car Rent</title>
</head>


<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
<h3 class="form-control">Car Rent</h3>

<p ><b>Reasonable prices.</b></p>
<p>Thanks to favorable rates, our customers save on car rental without sacrificing comfort and safety. Low prices for car rental allow individuals to move freely throughout the country, not owning a personal car, and businesses - to optimize the cost of maintaining a fleet.

<p></p><b>Convenient service.</b>
<p>In the company you can order a specific car for a specific time without paperwork and bail. Booking is a 100% guarantee that at the right time the ordered car will be delivered directly to your doors: on time, with a clean interior and a full tank.

<p></p><b>New cars.</b>
<p>All cars in our fleet are modern models of 2015–2017. release. They are in excellent technical condition, equipped with GPS navigation and Wi-Fi, fully insured (CASCO, OSAGO, driver’s life insurance)
</p>

<form>
    <input type="button" value="Choose a car" onClick='location.href="http://localhost:8080/carList"'>
</form>

</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>