<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaRespuestaModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.RolRespuestaModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.GeneroRespuestaModelo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Crear emlpleado</title>

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
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-text mx-3">Sistema de asistencia</div>
                </a>
                <hr class="sidebar-divider my-0">

                <li class="nav-item">
                    <a class="nav-link" href="index.html">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Inicio</span>
                    </a>
                </li>

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
                    <div id="collapseUtilities" class="collapse show" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Empleados</h6>
                            <a class="collapse-item active" href="<%=request.getContextPath()%>/empleados">Empleados</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/areas">Áreas</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/roles">Roles</a>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/generos">Géneros</a>
                        </div>
                    </div>
                </li>

                <hr class="sidebar-divider">

                <div class="sidebar-heading">
                    Asistencia
                </div>
                <li class="nav-item">
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="tables.html">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tables</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto"> 

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
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
                        <h1 class="h3 mb-4 text-gray-800">Crear empleado</h1>

                        <%
                            String mensajeError = (String) request.getAttribute("error");
                            if (mensajeError != null && !mensajeError.isEmpty()) {
                        %>
                        <div class="alert alert-danger">
                            <%= mensajeError%>
                        </div>
                        <%
                            }
                        %>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Registro</h6>
                            </div>
                            <div class="card-body"> 
                                <form action="<%=request.getContextPath()%>/empleados/crear" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-6"> 
                                            <div class="form-group">
                                                <label for="nombre">Nombre:</label>
                                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="apellido">Apellido:</label>
                                                <input type="text" class="form-control" id="apellido" name="apellido" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Correo:</label>
                                                <input type="email" class="form-control" id="email" name="email" required>
                                            </div> 
                                            <div class="form-group">
                                                <label for="contrasena">Contraseña:</label>
                                                <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="foto">Foto:</label>
                                                <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                                                <small class="form-text text-muted">Subir foto del empleado.</small>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="fechaNacimiento">Fecha de nacimiento:</label>
                                                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="generoId">Género:</label>
                                                <select class="form-control" id="generoId" name="generoId" required>
                                                    <% ArrayList<GeneroRespuestaModelo> generos = (ArrayList<GeneroRespuestaModelo>) request.getAttribute("generos"); %>
                                                    <% for (GeneroRespuestaModelo genero : generos) {%>
                                                    <option value="<%=genero.getId()%>"><%=genero.getNombre()%></option>
                                                    <% } %> 
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="direccion">Dirección:</label>
                                                <input type="text" class="form-control" id="direccion" name="direccion" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="telefono">Teléfono:</label>
                                                <input type="text" class="form-control" id="telefono" name="telefono" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="rolId">Rol:</label>
                                                <select class="form-control" id="rolId" name="rolId" required>
                                                    <% ArrayList<RolRespuestaModelo> roles = (ArrayList<RolRespuestaModelo>) request.getAttribute("roles"); %>
                                                    <% for (RolRespuestaModelo rol : roles) {%>
                                                    <option value="<%=rol.getId()%>"><%=rol.getNombre()%></option>
                                                    <% } %> 
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="areaId">Área:</label>
                                                <select class="form-control" id="areaId" name="areaId" required>
                                                    <% ArrayList<AreaRespuestaModelo> areas = (ArrayList<AreaRespuestaModelo>) request.getAttribute("areas"); %>
                                                    <% for (AreaRespuestaModelo area : areas) {%>
                                                    <option value="<%=area.getId()%>"><%=area.getNombre()%></option>
                                                    <% }%> 
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <button type="submit" class="btn btn-primary">Guardar</button>
                                            <a href="<%=request.getContextPath()%>/empleados" class="btn btn-secondary">Regresar</a>
                                        </div>
                                    </div>
                                </form>
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
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
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

    </body>
</html>
