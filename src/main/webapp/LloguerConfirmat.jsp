<!DOCTYPE html>
<html>
<head>
    <title>Confirmació de lloguer</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/EstilConfirmarLloguer.css">
</head>
<body>

<div class="container">
    <h1>Confirmació de lloguer</h1>

    <button type="button" id="btnConfirmarLloguer">Confirmar Lloguer</button>
    <button type="button" id="btnTornaLlistatJocs" onclick="volverALlistatJocs()">Torna LlistatJocs</button>

    <div id="confirmacion" class="message" style="display: none;">
        Lloguer confirmat! <span class="tic">&#10004;</span>
    </div>

    <div id="errorConfirmacion" class="message error-message" style="display: none;">
        Error al confirmar el lloguer, per favor intenta-ho de nou. <span class="cross">&#10008;</span>
    </div>

    <div id="responseContainer" style="display: none;">
       
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#btnConfirmarLloguer").click(function() {
            // Obtener los parámetros del URL
            var nomUsuari = '${UsuariCredencialsBean.username}';
            var idUsu = '${UsuariCredencialsBean.id}';
            var today = new Date();
            var returnDate = new Date(today);
            returnDate.setDate(returnDate.getDate() + 15);
            var diaRetorn = returnDate.toISOString().split('T')[0];
            var preuLloguer = <%= request.getParameter("preuLloguer") %>;
            var contrasenya = '${UsuariCredencialsBean.password}';
            var jocs1 = <%= request.getParameter("juegos") %>;
            
            
            // Realizar la solicitud POST para confirmar el lloguer
            $.ajax({
                url: 'http://localhost:8080/Homework1/webresources/rest/api/v1/rental',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    dia: diaRetorn,
                    jocs: jocs1,
                    preuTotal: preuLloguer,
                    usuari: {
                        contrasenya: contrasenya,
                        id: idUsu,
                        nomUsuari: nomUsuari
                    }
                }),
                success: function(data) {
                    console.log("Lloguer confirmat exitosament:", data);
                    $("#btnConfirmarLloguer").hide(); // Oculta el botón después de confirmar el lloguer
                    $("#confirmacion").fadeIn().delay(5000).fadeOut(); // Aumenta la duración del mensaje de confirmación
                    mostrarRespuesta(data);
                },
                error: function(error) {
                    console.error("Error en la llamada al servidor:", error);
                    $("#errorConfirmacion").fadeIn().delay(5000).fadeOut(); // Aumenta la duración del mensaje de error
                    mostrarRespuesta(error.responseJSON);
                }
            });
        });
    });
    
    function mostrarRespuesta(respuesta) {
        var responseHTML = '';

        // Recorre solo los primeros 3 elementos de la respuesta
        for (var i = 0; i < 3; i++) {
            // Dividir el elemento en clave y valor
            var keyValue = respuesta[i].split(':');

            // Formatear y agregar el contenido a responseHTML
            if (keyValue[0].trim() === "Jocs") {
                // Formatear la cadena de juegos
                var gamesString = keyValue[1].trim().substring(1, keyValue[1].length - 2);
                responseHTML += '<p><strong>' + keyValue[0] + ':</strong> ' + gamesString + '</p>';
                // Si se ha confirmado el lloguer, eliminar el juego del carrito
                eliminarJuegoCarritoEnConfirmacion(gamesString);
            } else {
                responseHTML += '<p><strong>' + keyValue[0] + ':</strong> ' + keyValue[1] + '</p>';
            }
        }

    // Muestra el contenido en el contenedor responseContainer
    $("#responseContainer").show().html(responseHTML);
}


        function volverALlistatJocs() {
            // Redirigir a la página LlistatJocs.jsp
             window.location.href = 'LlistatJocs.jsp';
        }



</script>

</body>
</html>
