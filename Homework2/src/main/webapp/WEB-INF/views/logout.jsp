<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logout</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* Estilo para centrar el contenido */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f3f4f6;
            font-family: Arial, sans-serif;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
            margin-bottom: 20px;
        }
        button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #45a049;
        }
        #btnCancel {
            background-color: #f44336;
            margin-left: 20px;
        }
        #btnCancel:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tanca Sessió</h1>
        <p id="MisatgeTancar"> Estàs segur de que vols tancar la sessió? </p>
        <!-- Botón con mensaje de confirmación -->
        <button id="btnConfirm">Si</button>
        <!-- Botón para cancelar y redirigir a la página inicial -->
        <button id="btnCancel">No</button>
    </div>

    <script>
        $(document).ready(function() {
            // Función para realizar la solicitud POST y redirigir después de confirmar
            function logoutAndRedirect() {
                // Realizar la solicitud POST para cerrar sesión
                $.ajax({
                    url: "http://localhost:8080/Homework2/Web/LogIn/LogOut", // Ruta a la que se enviará la solicitud POST
                    type: "POST", // Método de la solicitud
                    success: function(response) {
                        // Manejar la respuesta si es necesario
                        console.log(response);
                     
                    },
                    error: function(xhr, status, error) {
                        // Manejar errores si es necesario
                        console.error(xhr.responseText);
                        // window.location.href = 'LlistatJocs.jsp';
                    }
                });
            }

            // Agregar un evento de clic al botón de confirmación
            $("#btnConfirm").click(function() {
                // Mostrar un mensaje de confirmación y realizar la acción si el usuario confirma
                logoutAndRedirect();
                window.location.href = 'LlistatJocs.jsp'; // Ruta de la página a la que se redirige después de cerrar sesión
                
            });

            // Agregar un evento de clic al botón de cancelar
            $("#btnCancel").click(function() {
                // Redirigir a la página inicial si el usuario cancela
                window.location.href = 'LlistatJocs.jsp'; // Ruta de la página inicial
            });
        });
    </script>
</body>
</html>
