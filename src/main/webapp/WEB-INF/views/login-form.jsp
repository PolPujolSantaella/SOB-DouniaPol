<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

<!-- FontAwesome -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"/>
</head>
<body>
    <div class="container">
        <div class="col-md-offset-2 col-md-7">
            <h2 class="text-center">Log In</h2>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">Log In</div>
                </div>
                <div class="panel-body">
                    <form action="${mvc.uri('log-in')}" class="form-horizontal" method="POST">
                        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}" />
                        <div class="form-group">
                            <label for="username" class="col-md-3 control-label">Username</label>
                            <div class="col-md-9">
                                <input type="text" name="username" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-md-3 control-label">Password</label>
                            <div class="col-md-9">
                                <input type="password" name="password" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input type="submit" value="Log In" class="btn btn-primary" />
                            </div>
                        </div>
                    </form>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">${error}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div class="container text-center">
       <a class="btn btn-sm btn-info text-white" href="<c:url value="/LlistatJocs.jsp" />">Llistat Jocs</a>
    </div>
</body>
</html>