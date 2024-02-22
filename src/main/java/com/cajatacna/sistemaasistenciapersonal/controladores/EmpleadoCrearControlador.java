package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.GeneroRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.RolRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AreaServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.GeneroServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.RolServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.utilidades.Utilidades;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "EmpleadoCrearControlador", urlPatterns = {"/empleados/crear"})
@MultipartConfig
public class EmpleadoCrearControlador extends HttpServlet {

    private final EmpeladoServicio empleadoServicio;
    private final AreaServicio areaServicio;
    private final GeneroServicio generoServicio;
    private final RolServicio rolServicio;

    public EmpleadoCrearControlador() {
        this.empleadoServicio = new EmpeladoServicio(
                new EmpleadoRepositorio(),
                new GeneroRepositorio(),
                new RolRepositorio(),
                new AreaRepositorio());
        this.areaServicio = new AreaServicio(new AreaRepositorio());
        this.generoServicio = new GeneroServicio(new GeneroRepositorio());
        this.rolServicio = new RolServicio(new RolRepositorio());
    }

    private void enviarMaestros(HttpServletRequest request) {
        ArrayList<AreaRespuestaModelo> areas = this.areaServicio.obtenerTodos();
        ArrayList<GeneroRespuestaModelo> generos = this.generoServicio.obtenerTodos();
        ArrayList<RolRespuestaModelo> roles = this.rolServicio.obtenerTodos();

        request.setAttribute("areas", areas);
        request.setAttribute("generos", generos);
        request.setAttribute("roles", roles);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.enviarMaestros(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoCrear.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EmpleadoModelo empleado = new EmpleadoModelo();
            empleado.setNombre(request.getParameter("nombre"));
            empleado.setApellido(request.getParameter("apellido"));
            empleado.setContrasena(request.getParameter("contrasena"));
            empleado.setDireccion(request.getParameter("direccion"));
            empleado.setTelefono(request.getParameter("telefono"));
            empleado.setEmail(request.getParameter("email"));
            empleado.setFechaNacimiento(request.getParameter("fechaNacimiento"));
            empleado.setGeneroId(Utilidades.obtenerInt(request.getParameter("generoId")));
            empleado.setRolId(Utilidades.obtenerInt(request.getParameter("rolId")));
            empleado.setAreaId(Utilidades.obtenerInt(request.getParameter("areaId")));

            try {
                empleado.setFoto(Utilidades.obtenerBytesDePart(request.getPart("foto")));
            } catch (ServletException | IOException e) {
                throw new AplicacionExcepcion("Error al procesar el archivo");
            }

            this.empleadoServicio.crear(empleado);

            response.sendRedirect(request.getContextPath() + "/empleados?mensajeCorrecto=Empleado creado correctamente");
        } catch (AplicacionExcepcion e) {
            this.enviarMaestros(request);
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoCrear.jsp");
            dispatcher.forward(request, response);
        }
    }
}
