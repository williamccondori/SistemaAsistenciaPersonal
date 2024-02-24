package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AsistenciaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AsistenciaServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.utilidades.Utilidades;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import javax.servlet.annotation.MultipartConfig;

@WebServlet(name = "AsistenciaRegistrarControlador", urlPatterns = { "/asistencia/registrar" })
@MultipartConfig
public class AsistenciaRegistrarControlador extends HttpServlet {

    private final AsistenciaServicio asistenciaServicio;

    public AsistenciaRegistrarControlador() {
        this.asistenciaServicio = new AsistenciaServicio(new EmpleadoRepositorio(), new AsistenciaRepositorio());
    }

    private boolean verificarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empleado") == null) {
            request.setAttribute("error", "Inicia sesión para acceder a esta página");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean estaAutenticado = this.verificarSesion(request, response);
        if (estaAutenticado) {
            HttpSession session = request.getSession(false);
            int empleadoId = (int) session.getAttribute("empleadoId");
            AsistenciaModelo asistencia = this.asistenciaServicio.verificarRegistro(empleadoId);
            if (asistencia != null) {
                request.setAttribute("asistencia", asistencia);
            }

            ArrayList<AsistenciaModelo> asistencias = this.asistenciaServicio.obtenerPorFechasYEmpleado(empleadoId, new Date(), new Date());
            request.setAttribute("asistencias", asistencias);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/asistencia/AsistenciaRegistrar.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean estaAutenticado = this.verificarSesion(request, response);
        if (estaAutenticado) {
            try {
                AsistenciaModelo asistencia = new AsistenciaModelo();
                asistencia.setId(Utilidades.obtenerInt(request.getParameter("id")));
                asistencia.setEmpleadoId(Utilidades.obtenerInt(request.getParameter("empleadoId")));
                asistencia.setFecha(new Date());
                Time horaActual = new Time(System.currentTimeMillis());
                asistencia.setHoraEntrada(horaActual);
                asistencia.setHoraSalida(horaActual);
                this.asistenciaServicio.registrar(asistencia);
                response.sendRedirect(request.getContextPath() + "/asistencia/registrar?estado=OK");
            } catch (AplicacionExcepcion e) {
                request.setAttribute("error", e.getMessage());
                this.doGet(request, response);
            }
        }
    }
}
