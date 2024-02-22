package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.AreaServicio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;

@WebServlet(name = "AreaControlador", urlPatterns = { "/areas" })
public class AreaControlador extends HttpServlet {
    private final IAreaRepositorio areaRepositorio;

    public AreaControlador() {
        this.areaRepositorio = new AreaRepositorio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AreaServicio areaServicio = new AreaServicio(this.areaRepositorio);
        ArrayList<AreaRespuestaModelo> areas = areaServicio.obtenerTodos();

        request.setAttribute("areas", areas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/areas/AreaInicio.jsp");
        dispatcher.forward(request, response);
    }
}
