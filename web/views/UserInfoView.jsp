<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<jsp:include page="header.jsp"/>

<c:choose>
    <c:when test="${empty sessionScope.user }">
        <h1><fmt:message key="acc.acc" bundle="${lang}"/></h1>
        <br/>
    </c:when>
    <c:when test="${not empty sessionScope.user}">
        <div align="center">
            <div class="container bootstrap snippet">
                <h3><fmt:message key="menu.hello" bundle="${lang}"/> ${sessionScope.user.name}</h3>
                <div class="text-center">
                    <%--<form method="GET" action="${pageContext.request.contextPath}/userInfo">--%>
                        <%--<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"--%>
                             <%--class="avatar img-circle img-thumbnail"--%>
                             <%--alt="avatar" name ="image">--%>
                        <%--<input type="file" class="text-center center-block file-upload">--%>
                    <%--</form>--%>
                </div>
            </div>
            <br>
            <fmt:message key="user.name" bundle="${lang}"/> <b>${sessionScope.user.name}</b>
            <br>
            <fmt:message key="user.lastName" bundle="${lang}"/> <b>${sessionScope.user.lastName } </b>
            <br>
            <fmt:message key="user.login" bundle="${lang}"/> <b>${sessionScope.user.login}</b>
            <br>
            <p>


            </p>
            <h5><u><fmt:message key="acc.orders" bundle="${lang}"/></u></h5>
            <table>
                <tr>
                    <th class="bg-success"><fmt:message key="order.driver" bundle="${lang}"/></th>
                    <th class="bg-success"><fmt:message key="car.status" bundle="${lang}"/></th>
                    <th class="bg-success"><fmt:message key="order.start.rent" bundle="${lang}"/></th>
                    <th class="bg-success"><fmt:message key="order.end.rent" bundle="${lang}"/></th>
                </tr>

                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td scope="col">${order.driver}</td>
                        <td scope="col">${order.status}</td>
                        <td scope="col">${order.startRent}</td>
                        <td scope="col">${order.endRent}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <p style="color: darkred" align="center"><fmt:message key="acc.info" bundle="${lang}"/></p>

    </c:when>
</c:choose>
<jsp:include page="footer.jsp"/>

</body>
<script src="../views/js/icon.js"></script>
</html>
