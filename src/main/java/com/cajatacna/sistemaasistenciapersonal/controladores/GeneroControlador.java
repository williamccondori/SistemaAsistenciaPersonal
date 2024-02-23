package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.GeneroModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.GeneroServicio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GeneroControlador", urlPatterns = {"/generos"})
public class GeneroControlador extends HttpServlet {

    private final GeneroServicio generoServicio;

    public GeneroControlador() {
        this.generoServicio = new GeneroServicio(new GeneroRepositorio());
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
            ArrayList<GeneroModelo> generos = generoServicio.obtenerTodos();
            request.setAttribute("generos", generos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/generos/GeneroInicio.jsp");
            dispatcher.forward(request, response);
        }
    }
}
