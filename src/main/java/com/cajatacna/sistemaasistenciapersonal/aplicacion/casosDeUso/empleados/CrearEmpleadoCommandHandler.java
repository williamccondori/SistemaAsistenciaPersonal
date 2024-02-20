package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados;

import javax.inject.Inject;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.EmpleadoRepositorio;

public class CrearEmpleadoCommandHandler {

    @Inject
    private EmpleadoRepositorio empleadoRepositorio;

    public CrearEmpleadoCommandHandler(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public void handle(CrearEmpleadoCommand command) {
        Empleado empleado = new Empleado();

        empleadoRepositorio.Crear(empleado);
    }
}
