<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Iniciar sesión</title>

        <!-- Custom fonts for this template-->
        <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
              rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%=request.getContextPath()%>/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="<%=request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>
    <body class="bg-gradient-primary">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6" style="min-height: 10rem">
                                    <div style="display: flex; justify-content: center; align-content: center; height: 100%">
                                        <img src="https://cmactacna.com.pe/wp-content/themes/cajatacna/img/logo.svg" alt="logo" width="200">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">¡Bienvenido!</h1>
                                        </div>

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

                                        <form action="<%=request.getContextPath()%>/login" method="post" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <input type="email" class="form-control form-control-user"
                                                       id="email" name="email" placeholder="Correo electrónico" required>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user"
                                                       id="contrasena" name="contrasena" placeholder="Contraseña" required>
                                            </div>
                                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                                Iniciar sesión
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
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