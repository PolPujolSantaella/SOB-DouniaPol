        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
        <head>
            <title>Carrito</title>
            <!-- Agrega tus estilos y scripts necesarios -->
            <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
             <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/EstilCarrito.css">
        </head>
        <body>
            <!-- Mostrar la lista de juegos en el carrito -->
            <ul id="carritoLista"></ul>

            <!-- Mostrar un mensaje de notificación -->
            <div id="mensajeCarrito"></div>
            <div id="m1"></div>
            <button id="btnFinalizarCompra">Finalizar Compra</button>
            <button class= "boto" onclick="volverALlistatJocs()">Volver a la Lista de Juegos</button>


            <script>
                 function volverALlistatJocs() {
                    // Redirigir a la página LlistatJocs.jsp
                    window.location.href = 'LlistatJocs.jsp';
                }

                $(document).ready(function() {
                    var idJoc = <%= request.getParameter("idJoc") %>;
                    var idUsu = '${UsuariCredencialsBean.username}';

                    var carritos = obtenerCarritos(); // Obtener la lista de carritos
                    if(idJoc===0){
                            obtenerCarritos();
                          actualizarListaCarrito();
                    }
                    // Obtener el precio del lloguer del juego
                    $.ajax({
                        url: 'http://localhost:8080/Homework1/webresources/rest/api/v1/game',
                        type: 'GET',
                        dataType: 'json',
                        success: function(jocsData) {
                            // Buscar el juego con el ID específico en el array
                            var juego = jocsData.find(function(joc) {
                                return joc.id === idJoc;
                            });

                            if (juego) {
                                // Verificar si existe un carrito para el usuario
                                if (!carritos[idUsu]) {
                                    carritos[idUsu] = [];
                                    console.log(idUsu);
                                }

                                // Verificar si el juego ya está en la lista antes de agregarlo
                                var juegoEnCarrito = carritos[idUsu].find(function(juegoCarrito) {
                                    return juegoCarrito.id === juego.id;
                                });

                                if (!juegoEnCarrito) {
                                    carritos[idUsu].push(juego);
                                    console.log(carritos[idUsu]);
                                    guardarCarritos(carritos); // Guardar la lista actualizada de carritos en el lado del cliente
                                    actualizarListaCarrito();
                                } else {
                                    actualizarListaCarrito();
                                }
                            }
                        },
                        error: function(error) {
                            console.error("Error en la llamada al servidor:", error);
                        }
                    });

                    // Agregar eventos de clic a los botones de eliminar
                    $(document).on("click", ".eliminarJuegoBtn", function() {
                        var idJuegoEliminar = $(this).data("id");
                        eliminarJuegoCarrito(idJuegoEliminar);
                    });

                    $("#btnFinalizarCompra").on("click", function() {
                    // Obtener la lista actualizada de carritos
                    var carritos = obtenerCarritos();
                    var lista = carritos[idUsu] || [];

                    // Redirigir a confirmacion_lloguer.jsp con la lista de juegos y los IDs
                    window.location.href = 'LloguerConfirmat.jsp?juegos=' + encodeURIComponent(JSON.stringify(lista)) + '&idJoc=' + idJoc + '&idUsu=' + idUsu;
                });

                    function obtenerCarritos() {
                        // Obtener la lista actual de carritos desde sessionStorage
                        var carritos = sessionStorage.getItem('carritos');
                        return carritos ? JSON.parse(carritos) : {};
                    }

                    function guardarCarritos(carritos) {
                        // Guardar la lista actual de carritos en sessionStorage
                        sessionStorage.setItem('carritos', JSON.stringify(carritos));
                    }

                    function actualizarListaCarrito() {
                        var carritos = obtenerCarritos();
                        var lista = carritos[idUsu] || [];

                        // Limpiar la lista en la interfaz gráfica
                        var carritoLista = $("#carritoLista");
                        carritoLista.empty(); // Limpiar la lista

                        // Agregar los elementos a la lista
                        for (var i = 0; i < lista.length; i++) {
                            var juego = lista[i];
                            var imageSrc = 'resources/img/' + juego.nom + '.jpg'; // Ruta de la imagen
                            var dotColor = juego.disponibilitat ? '#4CAF50' : '#FF0000';
                            var disp = juego.disponibilitat ? 'Disponible' : 'No Disponible';
                            var gameElement = $('<div class="game">' +
                                '<div class="game-image-container"><img src="' + imageSrc + '" class="game-image" data-nom="' + juego.nom + '" data-id="' + juego.id + '" data-disponibilitat="' + juego.disponibilitat + '" data-descripcio="' + juego.descripcio + '" data-consola="'+ juego.consola.name + '" data-topic="' + juego.topic.name + '" data-adreces="' + juego.adress + '" data-preu="'+ juego.preu + '"></div>' +
                                '<div class="game-details">' +
                                '<h2>' + juego.nom + " (" + juego.consola.name + ")" + '</h2>' +
                                '<p>' + Number(juego.preu).toLocaleString('es-ES', { style: 'currency', currency: 'EUR' }) + '</p>' +
                                '<p><span class="availability-dot" style="background-color:' + dotColor + '"></span>' + disp + '</p>' +
                                '<button class="eliminarJuegoBtn" data-id="' + juego.id + '">Eliminar</button>' +
                                '</div>' +
                                '</div>');

                            carritoLista.append(gameElement);
                        }

                        // Asignar evento clic al botón eliminar después de agregarlos al DOM
                        $(".eliminarJuegoBtn").off().on("click", function() {
                            var idJuegoEliminar = $(this).data("id");
                            eliminarJuegoCarrito(idJuegoEliminar);
                        });
                    }

                    function eliminarJuegoCarrito(idJuegoEliminar) {
                        // Eliminar el juego del carrito y actualizar la lista
                        var carritos = obtenerCarritos();

                        if (carritos[idUsu]) {
                            carritos[idUsu] = carritos[idUsu].filter(function(juego) {
                                return juego.id !== idJuegoEliminar;
                            });

                            guardarCarritos(carritos);
                            actualizarListaCarrito();

                            // Redirigir a la URL deseada si la lista está vacía
                            if (carritos[idUsu].length === 0) {
                                window.location.href = 'LlistatJocs.jsp';
                            }
                        }
                    }
                });
            </script>
        </body>
        </html>
