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
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>

<h3>Home Page</h3>

This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
<b>It includes the following functions:</b>
<ul>
    <li>Login</li>
    <li>Storing user information in cookies</li>
    <li>Car List</li>
    <li>Create Car</li>
    <li>Edit Car</li>
    <li>Delete Car</li>
</ul>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>