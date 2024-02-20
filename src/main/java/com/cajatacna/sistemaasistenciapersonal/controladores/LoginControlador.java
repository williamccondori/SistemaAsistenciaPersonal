package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.servicios.EmpleadoServicio;

public class LoginControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/Login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        EmpleadoServicio empleadoServicio = new EmpleadoServicio();
        int empleadoId = empleadoServicio.iniciarSesion(email, clave);

        if (empleadoId > 0) {
            Empleado empleadoModelo = empleadoServicio.obtenerEmpleado(empleadoId);
            if (empleadoModelo != null) {
                request.getSession().setAttribute("empleadoId", empleadoModelo.getId());

            } else {
                String mensaje = "Empleado no encontrado";
                response.sendRedirect("vistas/login/Login.jsp?mensaje=" + mensaje);
            }
        } else {
            String mensaje = "Credenciales incorrectas";
            response.sendRedirect("vistas/login/Login.jsp?mensaje=" + mensaje);
        }
    }
}
