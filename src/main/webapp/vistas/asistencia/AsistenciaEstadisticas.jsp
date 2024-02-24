<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AsistenciaModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Empleado</title>

        <!-- Custom fonts for this template-->
        <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%=request.getContextPath()%>/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="<%=request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>
    <body id="page-top">
        <div id="wrapper">


            <%
                String email = (String) session.getAttribute("empleado");
                String foto = (String) session.getAttribute("foto");
                int rolId = (int) session.getAttribute("rolId");
            %>

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/asistencia"">
                    <div class="sidebar-brand-text mx-3">Sistema de asistencia</div>
                </a>
                <hr class="sidebar-divider my-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/asistencia"">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Inicio</span>
                    </a>
                </li>
                <% if (rolId == 1) {%>
                <hr class="sidebar-divider">
                <div class="sidebar-heading">
                    Maestros
                </div>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-user"></i>
                        <span>Empleados</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Empleados</h6>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/empleados">Empleados</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/areas">Áreas</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/roles">Roles</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/generos">Géneros</a>
                        </div>
                    </div>
                </li>
                <% }%>
                <hr class="sidebar-divider">
                <div class="sidebar-heading">
                    Asistencia
                </div>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/asistencia/registrar"">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Registrar</span></a>
                </li>
                <% if (rolId == 1) {%>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/asistencia/estadisticas"">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Estadísticas</span></a>
                </li>
                <% }%>
            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                        <%=email%>
                                    </span> 
                                    <% if (foto != null) {%>
                                    <img class="img-profile rounded-circle" src="data:image/jpeg;base64,<%=foto%>" alt="Foto de perfil">
                                    <% } else {%>
                                    <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/img/undraw_profile.svg" alt="Foto de perfil">
                                    <% }%>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Cerrar sesión
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-4 text-gray-800">Estadísticas</h1>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Todos las asistencias</h6>
                            </div>
                            <div class="card-body">
                                <form method="get" class="mb-4">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="fechaInicio">Fecha de inicio:</label>
                                                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio">
                                            </div> 
                                        </div>
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="fechaFin">Fecha de fin:</label>
                                                <input type="date" class="form-control" id="fechaFin" name="fechaFin">
                                            </div> 
                                        </div>
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary">
                                                Buscar
                                            </button>
                                        </div>
                                    </div>
                                </form>


                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Empleado</th>
                                                <th>Foto</th>
                                                <th>Área</th>
                                                <th>Fecha</th>
                                                <th>Hora de entrada</th>
                                                <th>Hora de salida</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<AsistenciaModelo> asistencias = (ArrayList<AsistenciaModelo>) request.getAttribute("asistencias");
                                                ArrayList<EmpleadoModelo> empleados = (ArrayList<EmpleadoModelo>) request.getAttribute("empleados");
                                                if (asistencias == null) {
                                                    asistencias = new ArrayList<>();
                                                }
                                                for (AsistenciaModelo asistencia : asistencias) {
                                            %>
                                            <tr>
                                                <td><%= asistencia.getEmpleado().getNombre()%> <%= asistencia.getEmpleado().getApellido()%></td>
                                                <td>
                                                    <% if (asistencia.getEmpleado().getFotoBase64() != null) {%>
                                                    <img class="img-profile rounded-circle" src="data:image/jpeg;base64,<%= asistencia.getEmpleado().getFotoBase64()%>" alt="Foto de perfil" width="50" height="50">
                                                    <% } else {%>
                                                    <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/img/undraw_profile.svg" alt="Foto de perfil" width="50" height="50">
                                                    <% }%>
                                                </td>
                                                <td><%= asistencia.getEmpleado().getArea()%></td>
                                                <td><%= asistencia.getFecha()%></td>
                                                <td><%= asistencia.getHoraEntrada()%></td>
                                                <td><%= asistencia.getHoraSalida()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="row">

                            <div class="col-6"> 
                                <div class="card shadow mb-4" >
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Resumen de ASISTENCIAS</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="asistenciasChart" ></canvas>

                                    </div>
                                </div>
                            </div>

                            <div class="col-6"> 
                                <div class="card shadow mb-4" >
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Resumen de ASISTENCIAS SIN HORA DE SALIDA</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="asistencias2Chart"></canvas>

                                    </div>
                                </div>
                            </div>

                            <div class="col-4"> 
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Empleados por ÁREA</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="areaChart"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">  
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Empleados por ROL</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="rolChart"></canvas>

                                    </div>
                                </div>
                            </div>
                            <div class="col-4">  
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Empleados por GÉNERO</h6>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="generoChart"></canvas>

                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>&copy; Caja Municipal de Ahorro y Crédito Tacna</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">¿Está seguro de cerrar sesión?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Seleccione la opción "Cerrar sesión" parar terminar su sesión</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/logout">Cerrar sesión</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="<%=request.getContextPath()%>/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="<%=request.getContextPath()%>/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="<%=request.getContextPath()%>/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="<%=request.getContextPath()%>/js/demo/datatables-demo.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


        <script>
            <%
                ObjectMapper objectMapper = new ObjectMapper();
                String asistenciasJSON = objectMapper.writeValueAsString(asistencias);
                String empleadosJSON = objectMapper.writeValueAsString(empleados);
            %>
            var asistenciasArray = <%= asistenciasJSON%>;
            var empleados = <%= empleadosJSON%>;

            // Contadores para el número de empleados por área y por rol
            var areaCount = {};
            var rolCount = {};
            var generoCount = {};

            // Contar empleados por área y por rol
            empleados.forEach(item => {
                areaCount[item.area] = (areaCount[item.area] || 0) + 1;
                rolCount[item.rol] = (rolCount[item.rol] || 0) + 1;
                generoCount[item.genero] = (generoCount[item.rol] || 0) + 1;
            });

            // Obtener los valores para los gráficos
            var areaLabels = Object.keys(areaCount);
            var areaValues = Object.values(areaCount);

            var rolLabels = Object.keys(rolCount);
            var rolValues = Object.values(rolCount);

            var generoLabels = Object.keys(generoCount);
            var generoValues = Object.values(generoCount);

            // Configuración del gráfico de pastel para el número de empleados por área
            var areaChart = new Chart(document.getElementById('areaChart').getContext('2d'), {
                type: 'pie',
                data: {
                    labels: areaLabels,
                    datasets: [{
                            data: areaValues,
                            backgroundColor: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)'],
                        }]
                }
            });

            // Configuración del gráfico de pastel para el número de empleados por rol
            var rolChart = new Chart(document.getElementById('rolChart').getContext('2d'), {
                type: 'pie',
                data: {
                    labels: rolLabels,
                    datasets: [{
                            data: rolValues,
                            backgroundColor: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)'],
                        }]
                }
            });

            // Configuración del gráfico de pastel para el número de empleados por genero
            var generoChart = new Chart(document.getElementById('generoChart').getContext('2d'), {
                type: 'pie',
                data: {
                    labels: generoLabels,
                    datasets: [{
                            data: generoValues,
                            backgroundColor: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)'],
                        }]
                }
            });

            // Contar asistencias por fecha
            var asistenciasPorFecha = {};
            asistenciasArray.forEach(asistencia => {
                var fecha = new Date(asistencia.fecha).toLocaleDateString();
                asistenciasPorFecha[fecha] = (asistenciasPorFecha[fecha] || 0) + 1;
            });

            // Obtener los valores para el gráfico de barras
            var fechas = Object.keys(asistenciasPorFecha);
            var asistencias = Object.values(asistenciasPorFecha);

            // Configuración del gráfico de barras para las asistencias
            var asistenciasChart = new Chart(document.getElementById('asistenciasChart').getContext('2d'), {
                type: 'bar',
                data: {
                    labels: fechas,
                    datasets: [{
                            label: 'Asistencias',
                            data: asistencias,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });


            // Contar asistencias sin hora de salida por fecha
            var asistenciasSinHoraSalidaPorFecha = {};
            asistenciasArray.forEach(asistencia => {
                if (asistencia.horaSalida === null || asistencia.horaSalida === undefined) {
                    var fecha = new Date(asistencia.fecha).toLocaleDateString();
                    asistenciasSinHoraSalidaPorFecha[fecha] = (asistenciasSinHoraSalidaPorFecha[fecha] || 0) + 1;
                }
            });

            // Obtener los valores para el gráfico de barras
            var fechas = Object.keys(asistenciasSinHoraSalidaPorFecha);
            var asistencias = Object.values(asistenciasSinHoraSalidaPorFecha);

            // Configuración del gráfico de barras para las asistencias sin hora de salida
            var asistenciasChart = new Chart(document.getElementById('asistencias2Chart').getContext('2d'), {
                type: 'bar',
                data: {
                    labels: fechas,
                    datasets: [{
                            label: 'Asistencias sin Hora de Salida',
                            data: asistencias,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </body>
</html>
