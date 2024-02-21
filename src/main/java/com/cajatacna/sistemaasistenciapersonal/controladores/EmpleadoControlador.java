package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.obtenerTodos.ObtenerTodosEmpleadoQueryHandler;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.empleados.EmpleadoRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;

@WebServlet(name = "EmpleadoControlador", urlPatterns = {"/empleados"})
public class EmpleadoControlador extends HttpServlet {

    private final IEmpleadoRepositorio empleadoRepositorio;

    public EmpleadoControlador() {
        this.empleadoRepositorio = new EmpleadoRepositorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObtenerTodosEmpleadoQueryHandler obtenerTodosEmpleadoQueryHandler = new ObtenerTodosEmpleadoQueryHandler(
                this.empleadoRepositorio);

        ArrayList<EmpleadoRespuestaModelo> empleados = obtenerTodosEmpleadoQueryHandler.handle();

        request.setAttribute("empleados", empleados);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/empleados/EmpleadoInicio.jsp");
        dispatcher.forward(request, response);
    }
}
