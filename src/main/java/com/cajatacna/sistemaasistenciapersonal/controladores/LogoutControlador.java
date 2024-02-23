package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutControlador", urlPatterns = { "/logout" })
public class LogoutControlador extends HttpServlet {

    public LogoutControlador() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/LoginInicio.jsp");
        dispatcher.forward(request, response);
    }
}
