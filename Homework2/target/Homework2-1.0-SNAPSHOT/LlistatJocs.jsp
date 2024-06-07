<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Jocs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/EstilJocs.css">
</head>
<body>
    <img class="encabezado-izquierdo" src="${pageContext.request.contextPath}/resources/img/ETSEcentrat.png">
    
    <c:choose>
        <c:when test="${empty UsuariCredencialsBean.username}">
            <btnIniciarSesion1 id="btnIniciarSesion1">Iniciar Sesión</btnIniciarSesion1>
        </c:when>
        <c:otherwise>
            <div id="nombreUsuario">Benvingut ${UsuariCredencialsBean.username} !</div>
 
            <button id="btnVerCarrito">Ver Carrito</button>
            <btnCerrarSession id="btnCerrarSesion">Cerrar Sesión</btnCerrarSession>
            
        </c:otherwise>
    </c:choose>

    <h1>Llistat de jocs per llogar</h1>
   
    <div id="Joc"></div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#btnIniciarSesion1").click(function() {
                window.location.href = 'index2.jsp';
            });
$("#btnCerrarSesion").click(function() {
  
                        // Redirigir a una página después de cerrar sesión
                        window.location.href = 'logout'; // Redirigir a LlistatJocs.jsp después de cerrar sesión
                
          
});


            var userName ='${UsuariCredencialsBean.username}';
            console.log(userName);

            var loggedIn = '${UsuariCredencialsBean.auth}';

            if (loggedIn && userName) {
          
            
                var usuarioAutenticado = loggedIn ;
                
                console.log(loggedIn);
                
                if (usuarioAutenticado) {
                    document.getElementById('btnVerCarrito').style.display = 'block';
                } else {
                    document.getElementById('btnVerCarrito').style.display = 'none';
                }
                
                document.getElementById('btnVerCarrito').addEventListener('click', function() {
                    if (usuarioAutenticado) {
                        window.location.href = 'Carrito.jsp?idUsu=' + userName + '&idJoc=' + encodeURIComponent(0);
                    } else {
                        window.location.href = 'index2.jsp';
                    }
                });

                $("#btnIniciarSesion1").hide();

                $("#nombreUsuario").text('Benvinguda '+userName +'!');
                $("#nombreUsuario").show();
                $("#btnCerrarSesionContainer").show();
                $("#btnCerrarSesion").show();
            }
            
            $.ajax({
                url: 'http://localhost:8080/Homework1/webresources/rest/api/v1/game',
                type: 'GET',
                success: function(data) {
                    $.each(data, function(index, jocs) {
                        var imageSrc = 'resources/img/' + jocs.nom + '.jpg';
                        var disp = jocs.disponibilitat ? 'Disponible' : 'No Disponible';
                        var dotColor = jocs.disponibilitat ? '#4CAF50' : '#FF0000';

                        var gameElement = $('<div class="game">' +
                            '<div class="game-image-container"><img src="' + imageSrc + '" class="game-image" data-nom="' + jocs.nom + '" data-id="' + jocs.id + '" data-disponibilitat="' + disp + '" data-descripcio="' + jocs.descripcio + '"data-consola="'+ jocs.consola.name + '"data-topic="' + jocs.topic.name + '" data-adreces="' + jocs.adress + '" data-preu="'+ jocs.preu + '"></div>' +
                            '<div class="game-details">' +
                            '<h2>' + jocs.nom + " (" + jocs.consola.name + ")" + '</h2>' +
                            '<p>' + Number(jocs.preu).toLocaleString('es-ES', { style: 'currency', currency: 'EUR' }) + '</p>' +
                            '<p><span class="availability-dot" style="background-color:' + dotColor + '"></span>' + disp + '</p>' +
                            '</div>' +
                            '</div>');

                        $('#Joc').append(gameElement);
                        index++;
                    });
                    
                    $('.game-image').on('click', function() {
                        var nombreJuego = $(this).data('nom');
                        var idJoc = $(this).data('id');
                        var disponibilitat = $(this).data('disponibilitat');
                        var dot;  
                        if (disponibilitat === "Disponible") {
                            dot = "#4CAF50"; 
                        } else {
                            dot = "#FF0000"; 
                        }
                        
                        var descripcion = $(this).data('descripcio');
                        var consola = $(this).data('consola');
                        var topicName = $(this).data('topic');
                        var adreces = $(this).data('adreces');
                        var preu = $(this).data('preu');

                        window.location.href = 'DetallsJoc.jsp?juego=' + encodeURIComponent(nombreJuego) +
                            '&idJoc='+encodeURIComponent(idJoc)+
                            '&dot=' + encodeURIComponent(dot) +
                            '&disponibilitat=' + encodeURIComponent(disponibilitat) +
                            '&descripcio=' + encodeURIComponent(descripcion) +
                            '&topic=' + encodeURIComponent(topicName) +
                            '&preu=' + encodeURIComponent(preu) +
                            '&consola=' + encodeURIComponent(consola) + 
                            '&adreces=' + encodeURIComponent(adreces);
                    });
                },
                error: function(error) {
                    console.log('Error:', error.responseText);
                }
            });
        });
    </script>
</body>
</html>
