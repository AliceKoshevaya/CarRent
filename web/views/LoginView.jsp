<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/jspf/include.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal">
                    <span class="heading"><fmt:message key="sing.in" bundle="${lang}"/></span>
                    <c:if test="${not empty errorMessageLogin}">
                        <c:out value="${errorMessageLogin}"/>
                    </c:if>
                    <c:out value="${errorMessage}"/>
                    <div class="form-group">
                        <input class="form-control" name="login"
                               placeholder="<fmt:message key="reg.login" bundle="${lang}"/>"/>
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="inputPassword" name="password"
                               placeholder="<fmt:message key="reg.password" bundle="${lang}"/>"/>
                        <i class="fa fa-lock"></i>
                        <a href="#" class="fa fa-question-circle"></a>
                    </div>
                    <div class="form-group">
                        <img src="<c:url value="${pageContext.request.contextPath}/stickyCaptcha.png" />"><br />
                        <input name="answer" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default"><fmt:message key="menu.door"
                                                                                   bundle="${lang}"/></button>
                    </div>
                </form>
            </div>

        </div><!-- /.row -->
    </div>


</body>
</html>