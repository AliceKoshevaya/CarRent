<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../views/fspf/include.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<p class="form-control"><fmt:message key="reg.name" bundle="${lang}"/></p>
<div class="container">
    <div class="row main-form">
        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <div class="form-group">
                <c:if test="${not empty errorMessageLogin}">
                    <c:out value="${errorMessageLogin}"/>
                </c:if>
                <c:out value="${errorMessage}"></c:out>
                <label for="name" class="cols-sm-2 control-label"> <fmt:message key="reg.login"
                                                                                bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="login"
                               placeholder="<fmt:message key="reg.enter.login" bundle="${lang}"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label"> <fmt:message key="reg.password"
                                                                                    bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="<fmt:message key="reg.enter.pass" bundle="${lang}"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm" class="cols-sm-2 control-label"> <fmt:message key="reg.confim.password"
                                                                                   bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="confirm" id="confirm"
                               placeholder="<fmt:message key="reg.enter.confirm.pass" bundle="${lang}"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label"> <fmt:message key="reg.username"
                                                                                bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="fname" id="name"
                               placeholder="<fmt:message key="reg.enter.name" bundle="${lang}"/>"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="username" class="cols-sm-2 control-label"> <fmt:message key="reg.lastname"
                                                                                    bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="lname" id="username"
                               placeholder=<fmt:message key="reg.enter.lastname" bundle="${lang}"/>"/>
                    </div>
                </div>
            </div>

            <div class=" form-group">
                        <label for="username" class="cols-sm-2 control-label"> <fmt:message key="reg.thirdname"
                                                                                            bundle="${lang}"/></label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="tname"
                                       placeholder="<fmt:message key="reg.enter.thirdname" bundle="${lang}"/>"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <input type="submit" value=
                        <fmt:message key="reg.apply" bundle="${lang}"/> id="button" class="btn btn-primary btn-lg btn-block login-button />
                <a href="${pageContext.request.contextPath}/">
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
