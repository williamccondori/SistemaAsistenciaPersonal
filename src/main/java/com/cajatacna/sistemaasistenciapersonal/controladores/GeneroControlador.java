package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.generos.GeneroRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.GeneroServicio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;

@WebServlet(name = "GeneroControlador", urlPatterns = { "/generos" })
public class GeneroControlador extends HttpServlet {
    private final IGeneroRepositorio generoRepositorio;

    public GeneroControlador() {
        this.generoRepositorio = new GeneroRepositorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GeneroServicio generoServicio = new GeneroServicio(this.generoRepositorio);
        ArrayList<GeneroRespuestaModelo> generos = generoServicio.obtenerTodos();

        request.setAttribute("generos", generos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/generos/GeneroInicio.jsp");
        dispatcher.forward(request, response);
    }
}
