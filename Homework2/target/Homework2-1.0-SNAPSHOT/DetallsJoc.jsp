<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles del Juego</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/EstilDetalls.css">
</head>
<body>
    	
    <div class="game-details-container">
        <% 
            String idJoc = request.getParameter("idJoc");
            String juego = request.getParameter("juego");
            String disponibilitat = request.getParameter("disponibilitat");
            String descripcio = request.getParameter("descripcio");
            String topic = request.getParameter("topic");
            String adreces = request.getParameter("adreces");
            String preu = request.getParameter("preu"); 
            String consola = request.getParameter("consola"); 
            String color = request.getParameter("dot");
        %>
        <div class="game-image-container">
            <img class="game-image" src="${pageContext.request.contextPath}/resources/img/<%= juego %>.jpg" alt="<%= juego %> Image" />
            <p><strong>Descripción:</strong> <%= descripcio %></p>
        </div>
        <div class="game-details">
            <h1><%= juego %>  (<%= consola %>) </h1>
            <p><strong>Preu:</strong> <%= preu %>&euro;</p>
            <p><span class="availability-dot" style="background-color:<%= color %>"></span> <%= disponibilitat %></p>
            <p><strong>Tipo:</strong> <%= topic %></p>
            <p><strong>Dirección:</strong> <%= adreces %></p>
            <button onclick="agregarACesta('<%= juego %>')">AÑADIR A LA CESTA</button>
            <button id="btnVerCarrito">Ver Carrito</button>
        </div>
        <button class= "boto" onclick="volverALlistatJocs()">Volver a la Lista de Juegos</button>
    </div>  

    <script>
        var username = '${UsuariCredencialsBean.username}';
        var loggedIn = ${UsuariCredencialsBean.auth};
         
        var idJoc = '<%= idJoc %>'; // Obtener el valor de idJoc desde los parámetros GET

        // Verificar si el usuario está autenticado (puedes ajustar esto según tu lógica de autenticación)
        var usuarioAutenticado = loggedIn;
        
        console.log(usuarioAutenticado);
        // Mostrar u ocultar botones según el estado de la sesión
        if (usuarioAutenticado === true) {
            document.getElementById('btnVerCarrito').style.display = 'block';
        } else {
            document.getElementById('btnVerCarrito').style.display = 'none';
        }

        // Agregar evento al botón de ver carrito
        document.getElementById('btnVerCarrito').addEventListener('click', function() {
            // Verificar si el usuario está autenticado antes de redirigir al carrito
            if (usuarioAutenticado === true) {
                window.location.href = 'Carrito.jsp?idUsu=' + username + '&idJoc=' + encodeURIComponent(0);
            }
        });

        function agregarACesta() {
            // Verificar si el usuario está autenticado
            if (usuarioAutenticado === true) { 
                    window.location.href = 'Carrito.jsp?idUsu=' + username + '&idJoc=' + encodeURIComponent(idJoc);
            } else {
                // Redirigir a la página de inicio de sesión con idJoc como parámetro
                window.location.href = 'index2.jsp';
            }
        }
        
        function volverALlistatJocs() {
            // Redirigir a la página LlistatJocs.jsp
            window.location.href = 'LlistatJocs.jsp';
        }
    </script>
</body>
</html> 
