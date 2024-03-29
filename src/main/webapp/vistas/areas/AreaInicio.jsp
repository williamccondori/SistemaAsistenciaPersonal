<%@page import="com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaModelo"%>
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

        <title>Áreas</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

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
                    <div id="collapseUtilities" class="collapse show" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Empleados</h6>
                            <a class="collapse-item" href="<%=request.getContextPath()%>/empleados">Empleados</a>
                            <a class="collapse-item active" href="<%=request.getContextPath()%>/areas">Áreas</a>
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
                <li class="nav-item">
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
                        <h1 class="h3 mb-4 text-gray-800">Áreas</h1>

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Todas las áreas</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Nombre</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<AreaModelo> areas = (ArrayList<AreaModelo>) request.getAttribute("areas");
                                                for (AreaModelo area : areas) {
                                            %>
                                            <tr>
                                                <td><%= area.getId()%></td>
                                                <td><%= area.getNombre()%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>
</html>
