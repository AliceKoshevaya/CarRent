
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Rejection reason</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<form method="post" action="/reason"> <input type="hidden" name="idOrder" value="${idOrder}"/>
    <div class="com-lg-1">
    <p><fmt:message key="menu.reason" bundle="${lang}"/><Br>
        <textarea name="comment" cols="40" rows="3"></textarea></p>
    <p>
        <input type="submit" value="<fmt:message key="reason.send" bundle="${lang}"/>" />
        <input type="reset" value="<fmt:message key="reason.clear" bundle="${lang}"/>" />
    </p>
    </div>
</form>
</body>
</html>
