package com.cajatacna.sistemaasistenciapersonal.controladores;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.utilidades.Utilidades;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmpleadoEliminarControlador", urlPatterns = {"/empleados/eliminar"})
@MultipartConfig
public class EmpleadoEliminarControlador extends HttpServlet {

    private final EmpeladoServicio empleadoServicio;

    public EmpleadoEliminarControlador() {
        this.empleadoServicio = new EmpeladoServicio(
                new EmpleadoRepositorio(),
                new GeneroRepositorio(),
                new RolRepositorio(),
                new AreaRepositorio());
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
            int empleadoId = Utilidades.obtenerInt(request.getParameter("id"));
            EmpleadoModelo empleado = this.empleadoServicio.obtenerPorId(empleadoId);
            request.setAttribute("empleado", empleado);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoEliminar.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean estaAutenticado = this.verificarSesion(request, response);
        if (estaAutenticado) {
            try {
                int empleadoId = Utilidades.obtenerInt(request.getParameter("id"));
                this.empleadoServicio.eliminar(empleadoId);

                response.sendRedirect(request.getContextPath() + "/empleados?estado=OK");
            } catch (AplicacionExcepcion e) {
                request.setAttribute("error", e.getMessage());
                this.doGet(request, response);
            }
        }
    }
}
