package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AsistenciaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AsistenciaServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AsistenciaEstadisticasControlador", urlPatterns = {"/asistencia/estadisticas"})
@MultipartConfig
public class AsistenciaEstadisticasControlador extends HttpServlet {

    private final EmpeladoServicio empleadoServicio;
    private final AsistenciaServicio asistenciaServicio;

    public AsistenciaEstadisticasControlador() {
        this.empleadoServicio = new EmpeladoServicio(
                new EmpleadoRepositorio(),
                new GeneroRepositorio(),
                new RolRepositorio(),
                new AreaRepositorio());
        this.asistenciaServicio = new AsistenciaServicio(new EmpleadoRepositorio(), new AsistenciaRepositorio());
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
            String fechaInicioS = request.getParameter("fechaInicio");
            String fechaFinS = request.getParameter("fechaFin");

            Date fechaInicio = null;
            Date fechaFin = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (fechaInicioS != null && !fechaInicioS.isEmpty()) {
                    fechaInicio = dateFormat.parse(fechaInicioS);
                }

                if (fechaFinS != null && !fechaFinS.isEmpty()) {
                    fechaFin = dateFormat.parse(fechaFinS);
                }
            } catch (ParseException e) {
                fechaInicio = new Date();
                fechaFin = new Date();
            }

            request.setAttribute("fechaInicio", fechaInicio);
            request.setAttribute("fechaFin", fechaFin);

            ArrayList<AsistenciaModelo> asistencias = this.asistenciaServicio.obtenerPorFechas(fechaInicio, fechaFin);
            request.setAttribute("asistencias", asistencias);

            ArrayList<EmpleadoModelo> empleados = this.empleadoServicio.obtenerTodos();
            request.setAttribute("empleados", empleados);

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/vistas/asistencia/AsistenciaEstadisticas.jsp");
            dispatcher.forward(request, response);
        }
    }
}
