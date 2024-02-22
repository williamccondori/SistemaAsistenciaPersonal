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
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "EmpleadoEliminarControlador", urlPatterns = { "/empleados/eliminar" })
@MultipartConfig
public class EmpleadoEliminarControlador extends HttpServlet {
    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IAreaRepositorio areasRepositorio;
    private final IGeneroRepositorio generoRepositorio;
    private final IRolRepositorio rolRepositorio;

    public EmpleadoEliminarControlador() {
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

    private void enviarEmpleado(HttpServletRequest request, EmpleadoModelo empleado) {
        request.setAttribute("empleado", empleado);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empleadoId = Utilidades.obtenerInt(request.getParameter("id"));

        EmpeladoServicio empeladoServicio = new EmpeladoServicio(
                this.empleadoRepositorio,
                this.generoRepositorio,
                this.rolRepositorio,
                this.areasRepositorio);
        EmpleadoModelo empleado = empeladoServicio.obtenerPorId(empleadoId);
        this.enviarMaestros(request);
        this.enviarEmpleado(request, empleado);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoActualizar.jsp");
        dispatcher.forward(request, response);
    }
}
