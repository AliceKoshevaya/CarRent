<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 05.11.2018
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>All users</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/userList">
    <table class="table table-lg">
        <thead>
        <tr class="table-primary">
            <th scope="col">Characteristic</th>
            <th scope="col">Value</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="table-primary">
                <td>Id:</td>
                <td name = "idUser">${user.id}</td>
            </tr>
            <tr class="table-primary">
                <td>Username:</td>
                <td>${user.name}</td>
            </tr>
            <tr class="table-primary">
                <td>LastName:</td>
                <td>${user.lastName}</td>
            </tr>
            <tr class="table-primary">
                <td>Login:</td>
                <td>${user.login}</td>
            </tr>
            <tr class="table-primary">
                <td>Role:</td>
                <td>${user.role.name}</td>
            </tr>
            <tr class="table-primary">
                <td><form method="post" action="/makeManager"><input type="hidden" name="idUser" value="${user.id}"/>
                    <input type="submit" value="Set a manager"/></form></td>
                <td><input type="submit" value="Block user"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
