<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.GeneroModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.RolModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaModelo"%>
<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
    EmpleadoModelo empleado = (EmpleadoModelo) request.getAttribute("empleado");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Actualizar empleado</title>

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
                    <a class="nav-link" href="<%=request.getContextPath()%>/asistencia/registrar"">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Registrar</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/asistencia/estadisticas"">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Estadísticas</span></a>
                </li>
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
                                    <%
                                        String email = (String) session.getAttribute("empleado");
                                        String foto = (String) session.getAttribute("foto");
                                    %>
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
                        <h1 class="h3 mb-4 text-gray-800">Actualizar empleado</h1>

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

                        <% if (empleado != null) {%>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Registro</h6>
                            </div>
                            <div class="card-body"> 
                                <form action="<%=request.getContextPath()%>/empleados/actualizar" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <input type="hidden" name="id" value="<%=empleado.getId()%>">
                                            <div class="form-group">
                                                <label for="nombre">Nombre:</label>
                                                <input type="text" class="form-control" id="nombre" name="nombre" required value="<%=empleado.getNombre()%>">
                                            </div>
                                            <div class="form-group">
                                                <label for="apellido">Apellido:</label>
                                                <input type="text" class="form-control" id="apellido" name="apellido" required value="<%=empleado.getApellido()%>">
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Correo:</label>
                                                <input type="email" class="form-control" id="email" name="email" required value="<%=empleado.getEmail()%>">
                                            </div> 
                                            <div class="form-group">
                                                <label for="contrasena">Contraseña:</label>
                                                <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="foto">Foto:</label>
                                                <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                                                <small class="form-text text-muted">Subir foto del empleado.</small>
                                                <% if (empleado.getFotoBase64() != null) {%>
                                                <img class="img-profile rounded-circle" src="data:image/jpeg;base64,<%= empleado.getFotoBase64()%>" alt="Foto de perfil" width="50" height="50">
                                                <% } else {%>
                                                <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/img/undraw_profile.svg" alt="Foto de perfil" width="50" height="50">
                                                <% }%>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="fechaNacimiento">Fecha de nacimiento:</label>
                                                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required value="<%=empleado.getFechaNacimiento()%>">
                                            </div>
                                            <div class="form-group">
                                                <label for="generoId">Género:</label>
                                                <select class="form-control" id="generoId" name="generoId" required>
                                                    <% ArrayList<GeneroModelo> generos = (ArrayList<GeneroModelo>) request.getAttribute("generos"); %>
                                                    <% for (GeneroModelo genero : generos) {%>
                                                    <option value="<%=genero.getId()%>" <% if (genero.getId() == empleado.getGeneroId()) {%> selected <% } %>>
                                                            <%=genero.getNombre()%>
                                                    </option>
                                                    <% }%> 
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="direccion">Dirección:</label>
                                                <input type="text" class="form-control" id="direccion" name="direccion" required value="<%=empleado.getDireccion()%>">
                                            </div>
                                            <div class="form-group">
                                                <label for="telefono">Teléfono:</label>
                                                <input type="text" class="form-control" id="telefono" name="telefono" required value="<%=empleado.getTelefono()%>">
                                            </div>
                                            <div class="form-group">
                                                <label for="rolId">Rol:</label>
                                                <select class="form-control" id="rolId" name="rolId" required>
                                                    <% ArrayList<RolModelo> roles = (ArrayList<RolModelo>) request.getAttribute("roles"); %>
                                                    <% for (RolModelo rol : roles) {%>
                                                    <option value="<%=rol.getId()%>" <% if (rol.getId() == empleado.getRolId()) {%> selected <% } %>>
                                                            <%=rol.getNombre()%></option>
                                                    <% }%> 
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="areaId">Área:</label>
                                                <select class="form-control" id="areaId" name="areaId" required>
                                                    <% ArrayList<AreaModelo> areas = (ArrayList<AreaModelo>) request.getAttribute("areas"); %>
                                                    <% for (AreaModelo area : areas) {%>
                                                    <option value="<%=area.getId()%>" <% if (area.getId() == empleado.getAreaId()) {%> selected <% } %>>
                                                            <%=area.getNombre()%></option>
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

                        <% } else {%>
                        <div class="alert alert-danger">
                            No se encontró el empleado.
                            <a href="<%=request.getContextPath()%>/empleados" class="btn btn-danger">Regresar</a>
                        </div>
                        <% }%>

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

    </body>
</html>
