package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.RolModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.RolServicio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RolControlador", urlPatterns = {"/roles"})
public class RolControlador extends HttpServlet {

    private final RolServicio rolServicio;

    public RolControlador() {
        this.rolServicio = new RolServicio(new RolRepositorio());
    }

    private boolean verificarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empleado") == null) {
            request.setAttribute("error", "Inicia sesión para acceder a esta página");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        } else {
            int rolId = (int) session.getAttribute("rolId");
            if (rolId != 1) {
                request.setAttribute("error", "No tienes permisos para acceder a esta página");
                response.sendRedirect(request.getContextPath() + "/asistencia");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean estaAutenticado = this.verificarSesion(request, response);
        if (estaAutenticado) {
            ArrayList<RolModelo> roles = rolServicio.obtenerTodos();
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/roles/RolInicio.jsp");
            dispatcher.forward(request, response);
        }
    }
}
