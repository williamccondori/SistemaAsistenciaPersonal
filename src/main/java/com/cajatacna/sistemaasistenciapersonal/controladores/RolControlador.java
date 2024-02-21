package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.roles.obtenerTodos.ObtenerTodosRolQueryHandler;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.roles.RolRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "RolControlador", urlPatterns = { "/roles" })
public class RolControlador extends HttpServlet {
    private final IRolRepositorio rolRepositorio;

    public RolControlador() {
        this.rolRepositorio = new RolRepositorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObtenerTodosRolQueryHandler obtenerTodosRolQueryHandler = new ObtenerTodosRolQueryHandler(
                this.rolRepositorio);

        ArrayList<RolRespuestaModelo> roles = obtenerTodosRolQueryHandler.handle();

        request.setAttribute("roles", roles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/roles/RolInicio.jsp");
        dispatcher.forward(request, response);
    }
}