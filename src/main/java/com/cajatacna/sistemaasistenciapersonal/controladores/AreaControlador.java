package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AreaServicio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AreaControlador", urlPatterns = {"/areas"})
public class AreaControlador extends HttpServlet {

    private final AreaServicio areaServicio;

    public AreaControlador() {
        this.areaServicio = new AreaServicio(new AreaRepositorio());
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
            ArrayList<AreaModelo> areas = areaServicio.obtenerTodos();
            request.setAttribute("areas", areas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/areas/AreaInicio.jsp");
            dispatcher.forward(request, response);
        }
    }
}
