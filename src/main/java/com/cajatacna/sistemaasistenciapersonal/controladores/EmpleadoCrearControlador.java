package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.areas.obtenerTodos.ObtenerTodosAreaQueryHandler;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.generos.obtenerTodos.ObtenerTodosGeneroQueryHandler;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.roles.obtenerTodos.ObtenerTodosRolQueryHandler;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.areas.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.generos.GeneroRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.roles.RolRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "EmpleadoCrearControlador", urlPatterns = { "/crear-empleado" })
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
        ObtenerTodosAreaQueryHandler obtenerTodosAreaQueryHandler = new ObtenerTodosAreaQueryHandler(
                this.areasRepositorio);
        ArrayList<AreaRespuestaModelo> areas = obtenerTodosAreaQueryHandler.handle();
        ObtenerTodosGeneroQueryHandler obtenerTodosGeneroQueryHandler = new ObtenerTodosGeneroQueryHandler(
                this.generoRepositorio);
        ArrayList<GeneroRespuestaModelo> generos = obtenerTodosGeneroQueryHandler.handle();
        ObtenerTodosRolQueryHandler obtenerTodosRolQueryHandler = new ObtenerTodosRolQueryHandler(
                this.rolRepositorio);
        ArrayList<RolRespuestaModelo> roles = obtenerTodosRolQueryHandler.handle();

        request.setAttribute("areas", areas);
        request.setAttribute("generos", generos);
        request.setAttribute("roles", roles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoCrear.jsp");
        dispatcher.forward(request, response);
    }
}
