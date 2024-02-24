package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;
import javax.servlet.annotation.MultipartConfig;

@WebServlet(name = "LoginControlador", urlPatterns = {"/login"})
@MultipartConfig
public class LoginControlador extends HttpServlet {

    private final EmpeladoServicio empleadoServicio;

    public LoginControlador() {
        this.empleadoServicio = new EmpeladoServicio(
                new EmpleadoRepositorio(),
                new GeneroRepositorio(),
                new RolRepositorio(),
                new AreaRepositorio());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/LoginInicio.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EmpleadoModelo empelado = this.empleadoServicio.autenticar(request.getParameter("email"), request.getParameter("contrasena"));
            HttpSession session = request.getSession();
            session.setAttribute("empleadoId", empelado.getId());
            session.setAttribute("empleado", empelado.getEmail());
            session.setAttribute("rolId", empelado.getRolId());
            session.setAttribute("foto", empelado.getFotoBase64());
            response.sendRedirect(request.getContextPath() + "/asistencia");
        } catch (AplicacionExcepcion e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/LoginInicio.jsp");
            dispatcher.forward(request, response);
        }
    }
}
