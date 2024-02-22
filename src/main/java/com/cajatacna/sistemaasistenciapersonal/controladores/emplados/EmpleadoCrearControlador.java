package com.cajatacna.sistemaasistenciapersonal.controladores.emplados;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.crearEmpleado.CrearEmpleadoCommand;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.areas.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.generos.GeneroRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.roles.RolRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AreaServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.GeneroServicio;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.RolServicio;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@MultipartConfig
@WebServlet(name = "EmpleadoCrearControlador", urlPatterns = { "/empleados/crear" })
public class EmpleadoCrearControlador extends HttpServlet {

    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IAreaRepositorio areasRepositorio;
    private final IGeneroRepositorio generoRepositorio;
    private final IRolRepositorio rolRepositorio;

    public EmpleadoCrearControlador() {
        this.empleadoRepositorio = new EmpleadoRepositorio();
        this.areasRepositorio = new AreaRepositorio();
        this.generoRepositorio = new GeneroRepositorio();
        this.rolRepositorio = new RolRepositorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Áreas.
        AreaServicio areaServicio = new AreaServicio(this.areasRepositorio);
        ArrayList<AreaRespuestaModelo> areas = areaServicio.obtenerTodos();

        // Géneros.
        GeneroServicio generoServicio = new GeneroServicio(this.generoRepositorio);
        ArrayList<GeneroRespuestaModelo> generos = generoServicio.obtenerTodos();

        // Roles.
        RolServicio rolServicio = new RolServicio(this.rolRepositorio);
        ArrayList<RolRespuestaModelo> roles = rolServicio.obtenerTodos();

        request.setAttribute("areas", areas);
        request.setAttribute("generos", generos);
        request.setAttribute("roles", roles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoCrear.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EmpeladoServicio empeladoServicio = new EmpeladoServicio(
                    this.empleadoRepositorio,
                    this.generoRepositorio,
                    this.rolRepositorio,
                    this.areasRepositorio);

            CrearEmpleadoCommand command = new CrearEmpleadoCommand();
            command.setNombre(request.getParameter("nombre"));
            command.setApellido(request.getParameter("apellido"));
            command.setContrasena(request.getParameter("contrasena"));
            command.setDireccion(request.getParameter("direccion"));
            command.setTelefono(request.getParameter("telefono"));
            command.setEmail(request.getParameter("email"));

            command.setGeneroId(
                    Integer.parseInt(
                            request.getParameter("generoId") != null
                                    ? request.getParameter("generoId")
                                    : "0"));
            command.setRolId(
                    Integer.parseInt(request.getParameter("rolId") != null
                            ? request.getParameter("rolId")
                            : "0"));
            command.setAreaId(
                    Integer.parseInt(request.getParameter("areaId") != null
                            ? request.getParameter("areaId")
                            : "0"));

            Part filePart = request.getPart("file");
            if (filePart != null) {
                InputStream fileContent = filePart.getInputStream();
                command.setFoto(fileContent.readAllBytes());
            }

            empeladoServicio.crear(command);

            response.sendRedirect(
                    request.getContextPath()
                            + "/empleados?mensajeCorrecto=Empleado creado correctamente");
        } catch (AplicacionExcepcion e) {
            response.sendRedirect(
                    request.getContextPath() + "/empleados/crear?mensajeError=" + e.getMessage());
        }
    }
}
