<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar asistencia</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.5.0/semantic.min.css" integrity="sha512-KXol4x3sVoO+8ZsWPFI/r5KBVB/ssCGB5tsv2nVOKwLg33wTFP3fmnXa47FdSVIshVTgsYk/1734xSk9aFIa4A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.5.0/semantic.min.js" integrity="sha512-Xo0Jh8MsOn72LGV8kU5LsclG7SUzJsWGhXbWcYs2MAmChkQzwiW/yTQwdJ8w6UA9C6EVG18GHb/TrYpYCjyAQw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
            // JavaScript para obtener la hora actual
            function obtenerHoraActual() {
                var fecha = new Date();
                var hora = fecha.getHours();
                var minutos = fecha.getMinutes();
                var segundos = fecha.getSeconds();

                // Formatear la hora para que siempre tenga dos dígitos
                hora = (hora < 10) ? "0" + hora : hora;
                minutos = (minutos < 10) ? "0" + minutos : minutos;
                segundos = (segundos < 10) ? "0" + segundos : segundos;

                // Mostrar la hora en el formato HH:mm:ss
                var horaActual = hora + ":" + minutos + ":" + segundos;
                document.getElementById("horaActual").innerHTML = horaActual;
            }

            // Llamar a la función para obtener la hora actual al cargar la página
            window.onload = function() {
                obtenerHoraActual();

                // Configurar un intervalo para actualizar la hora cada segundo (1000 milisegundos)
                setInterval(obtenerHoraActual, 1000);
            };
        </script>
    </head>
    <body>
        <div class="ui middle aligned center aligned grid" style="height: 100vh;">
            <div class="column" style="max-width: 450px;">
                <div class="ui container">
                    <div>
                        <img src="https://cmactacna.com.pe/wp-content/themes/cajatacna/img/logo.svg" alt="logo" width="150"/>
                    </div>
                    <h2 class="ui red image header">
                        <div class="content">
                            Registro de Asistencia
                        </div>
                    </h2>

                    <!-- Card informativo del usuario -->
                    <div class="ui card">
                        <div class="content">
                            <div class="header">Información del Usuario</div>
                            <div class="meta">Nombres y Apellidos</div>
                            <div class="description">
                                <p>Nombre Apellido</p>
                            </div>
                            <div class="meta">Área</div>
                            <div class="description">
                                <p>Nombre del Área</p>
                            </div>
                        </div>
                    </div>

                    <!-- Hora actual -->
                    <div class="ui message">
                        <p>Hora Actual: <b><span id="horaActual"></span></b></p>
                    </div>

                    <!-- Formulario de Registro de Asistencia -->
                    <form class="ui form">
                        <div class="ui stacked segment">
                            <div class="field">
                                <label>Hora de entrada:</label>
                                <input type="text" name="horaEntrada" id="horaEntrada" placeholder="HH:mm:ss" readonly>
                            </div>
                            <div class="field">
                                <label>Hora de salida:</label>
                                <input type="text" name="horaSalida" id="horaSalida" placeholder="HH:mm:ss" readonly>
                            </div>
                            <div class="ui fluid large red submit button" onclick="registrarAsistencia()">Registrar Asistencia</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            // Función para registrar la asistencia y actualizar la hora de salida
            function registrarAsistencia() {
                var horaSalida = obtenerHoraActual();
                document.getElementById("horaSalida").value = horaSalida;
                // Aquí puedes enviar los datos al servidor para el registro real de asistencia
                alert("Asistencia registrada. Hora de salida: " + horaSalida);
            }
        </script>
    </body>
</html>
