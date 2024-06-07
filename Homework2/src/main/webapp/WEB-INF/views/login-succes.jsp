<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Sign Up Success - SOB</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

</head>
<body>
    <div class="container">
        <div class="col-md-offset-2 col-md-7">
            <div class="text-center">
                <img class="mb-4" src="<c:url value="/resources/img/ETSEcentrat.png" />" alt="" width="134" height="92" />
            </div>
            <h1>Welcome, ${UsuariCredencialsBean.username}!</h1>
            <hr />
            <p class="text-md-start">
            </p>
            <!-- Aquí puedes añadir más contenido según tus necesidades -->
            <a class="btn btn-sm btn-info text-white" href="<c:url value="/LlistatJocs.jsp" />">Torna a Llistat Jocs</a>

        </div>
    </div>
</body>
</html>
