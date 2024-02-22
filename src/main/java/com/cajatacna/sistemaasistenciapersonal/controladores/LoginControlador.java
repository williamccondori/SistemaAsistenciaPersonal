package com.cajatacna.sistemaasistenciapersonal.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.LoginModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios.EmpeladoServicio;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios.RolRepositorio;

@WebServlet(name = "LoginControlador", urlPatterns = { "/login" })
public class LoginControlador extends HttpServlet {
    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IAreaRepositorio areasRepositorio;
    private final IGeneroRepositorio generoRepositorio;
    private final IRolRepositorio rolRepositorio;

    public LoginControlador() {
        this.empleadoRepositorio = new EmpleadoRepositorio();
        this.areasRepositorio = new AreaRepositorio();
        this.generoRepositorio = new GeneroRepositorio();
        this.rolRepositorio = new RolRepositorio();
    }

    private void enviarLogin(HttpServletRequest request) {
        LoginModelo login = new LoginModelo();
        login.setEmail(request.getParameter("email"));
        login.setContrasena(request.getParameter("contrasena"));
        request.setAttribute("login", login);
    }

    private LoginModelo recibirLogin(HttpServletRequest request) {
        LoginModelo empleado = new LoginModelo();
        empleado.setEmail(request.getParameter("email"));
        empleado.setContrasena(request.getParameter("contrasena"));
        return empleado;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/LoginInicio.jsp");
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

            LoginModelo loginModelo = this.recibirLogin(request);

            EmpleadoModelo empelado = empeladoServicio.autenticar(
                    loginModelo.getEmail(),
                    loginModelo.getContrasena());

            // Crear sesión.
            HttpSession session = request.getSession();
            session.setAttribute("empleado", empelado.getEmail());

            response.sendRedirect(request.getContextPath());
        } catch (AplicacionExcepcion e) {

            // Enviar login.
            this.enviarLogin(request);

            request.setAttribute("error", e.getMessage());

            // Redirigir a la vista.
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/login/LoginInicio.jsp");
            dispatcher.forward(request, response);
        }
    }
}
