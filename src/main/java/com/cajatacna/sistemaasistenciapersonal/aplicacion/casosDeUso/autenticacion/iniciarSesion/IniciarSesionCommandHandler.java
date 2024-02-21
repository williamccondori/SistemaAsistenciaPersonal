package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.autenticacion.iniciarSesion;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AutenticacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;

public class IniciarSesionCommandHandler {

    private final IEmpleadoRepositorio empleadoRepositorio;

    public IniciarSesionCommandHandler(
            IEmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public Empleado handle(IniciarSesionCommand command) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorEmail(command.getEmail());
        if (empleado == null) {
            throw new AutenticacionExcepcion();
        }

        if (!empleado.getContrasena().equals(command.getContrasena())) {
            throw new AutenticacionExcepcion();
        }

        return empleado;
    }

}
