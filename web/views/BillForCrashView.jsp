<%--
  Created by IntelliJ IDEA.
  User: ajiek
  Date: 06.11.2018
  Time: 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<html>
<head>
    <title>Bill for Crash</title>
</head>
<%@include file="../views/jspf/include.jspf" %>
<jsp:include page="header.jsp"/>
<body>
<p class="form-control"><fmt:message key="menu.bill" bundle="${lang}"/></p>
<div class="container">
    <div class="form-control form-control-lg">
        <form method="POST" action="${pageContext.request.contextPath}/crash">
            <div class="form-group">
            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label"><fmt:message key="bill.type" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="type" id="password" placeholder="<fmt:message key="enter.type" bundle="${lang}"/>" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label"><fmt:message key="bill.sum" bundle="${lang}"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="sum" id="name"  placeholder="<fmt:message key="enter.sum" bundle="${lang}"/>" />
                    </div>
                </div>
            </div>

            <div class="form-group ">
                <input type="hidden" name="idOrder" value="${idOrder}"/>
                <input type="submit" value= "Add new bill" id="button" class="btn btn-primary btn-lg btn-block login-button />
                <a href="${pageContext.request.contextPath}/">
            </div>
            </div>
    </form>
    </div>
</div>
</body>
</html>
