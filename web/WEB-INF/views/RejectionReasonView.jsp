<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 06.11.2018
  Time: 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Rejection reason</title>
</head>
<body>
<form method="post" action="/reason">
    <div class="com-lg-1">
    <p>Reason<Br>
        <textarea name="comment" cols="40" rows="3"></textarea></p>
    <p><input type="submit" value="Send">
        <input type="reset" value="Clean"></p>
    </div>
</form>
</body>
</html>
