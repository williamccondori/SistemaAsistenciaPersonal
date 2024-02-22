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
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "EmpleadoActualizarControlador", urlPatterns = { "/empleados/actualizar" })
@MultipartConfig
public class EmpleadoActualizarControlador extends HttpServlet {

    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IAreaRepositorio areasRepositorio;
    private final IGeneroRepositorio generoRepositorio;
    private final IRolRepositorio rolRepositorio;

    public EmpleadoActualizarControlador() {
        this.empleadoRepositorio = new EmpleadoRepositorio();
        this.areasRepositorio = new AreaRepositorio();
        this.generoRepositorio = new GeneroRepositorio();
        this.rolRepositorio = new RolRepositorio();
    }

    private void enviarMaestros(HttpServletRequest request) {
        AreaServicio areaServicio = new AreaServicio(this.areasRepositorio);
        ArrayList<AreaRespuestaModelo> areas = areaServicio.obtenerTodos();

        GeneroServicio generoServicio = new GeneroServicio(this.generoRepositorio);
        ArrayList<GeneroRespuestaModelo> generos = generoServicio.obtenerTodos();

        RolServicio rolServicio = new RolServicio(this.rolRepositorio);
        ArrayList<RolRespuestaModelo> roles = rolServicio.obtenerTodos();

        request.setAttribute("areas", areas);
        request.setAttribute("generos", generos);
        request.setAttribute("roles", roles);
    }

    private void enviarEmpleado(HttpServletRequest request) {
        EmpleadoModelo empleado = new EmpleadoModelo();
        empleado.setId(Utilidades.obtenerInt(request.getParameter("id")));
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
        request.setAttribute("empleado", empleado);
    }

    private EmpleadoModelo recibirEmpleado(HttpServletRequest request) {
        EmpleadoModelo empleado = new EmpleadoModelo();
        empleado.setId(Utilidades.obtenerInt(request.getParameter("id")));
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

        return empleado;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.enviarMaestros(request);
        this.enviarEmpleado(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoActualizar.jsp");
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
            EmpleadoModelo empleado = this.recibirEmpleado(request);
            empeladoServicio.crear(empleado);
            response.sendRedirect(
                    request.getContextPath() + "/empleados?mensajeCorrecto=Empleado creado correctamente");
        } catch (AplicacionExcepcion e) {

            // Enviar maestros y empleado.
            this.enviarMaestros(request);
            this.enviarEmpleado(request);

            request.setAttribute("error", e.getMessage());

            // Redirigir a la vista.
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoActualizar.jsp");
            dispatcher.forward(request, response);
        }
    }
}
