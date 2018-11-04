<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 25.10.2018
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/userInfo">
<jsp:include page="header.jsp"></jsp:include>


<h3>Hello: ${user.name}</h3>

Name: <b>${user.name}</b>
<br />
Last Name: ${user.lastName } <br />

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>