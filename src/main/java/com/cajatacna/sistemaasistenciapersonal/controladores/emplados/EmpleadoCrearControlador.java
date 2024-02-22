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

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.areas.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.empleados.CrearEmpleadoModelo;
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

            CrearEmpleadoModelo empleado = new CrearEmpleadoModelo();
            empleado.setNombre(request.getParameter("nombre"));
            empleado.setApellido(request.getParameter("apellido"));
            empleado.setContrasena(request.getParameter("contrasena"));
            empleado.setDireccion(request.getParameter("direccion"));
            empleado.setTelefono(request.getParameter("telefono"));
            empleado.setEmail(request.getParameter("email"));
            empleado.setFechaNacimiento(request.getParameter("fechaNacimiento"));
            empleado.setGeneroId(this.obtenerInt(request.getParameter("generoId")));
            empleado.setRolId(this.obtenerInt(request.getParameter("rolId")));
            empleado.setAreaId(this.obtenerInt(request.getParameter("areaId")));
            empleado.setFoto(this.obtenerFoto(request.getPart("file")));
            empeladoServicio.crear(empleado);

            response.sendRedirect(
                    request.getContextPath() + "/empleados?mensajeCorrecto=Empleado creado correctamente");
        } catch (AplicacionExcepcion e) {
            response.sendRedirect(request.getContextPath() + "/empleados/crear?mensajeError=" + e.getMessage());
        }
    }

    private byte[] obtenerFoto(Part part) {
        try {
            InputStream inputStream = part.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            return buffer;
        } catch (IOException e) {
            return null;
        }
    }

    private int obtenerInt(String valor) {
        return valor == null ? 0 : Integer.parseInt(valor);
    }
}
