package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.actualizarEmpleado;

import javax.inject.Inject;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.NoEncontradoExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.AreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.EmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.GeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.RolRepositorio;

public class ActualizarEmpeladoCommandHandler {
    @Inject
    private EmpleadoRepositorio empleadoRepositorio;
    @Inject
    private GeneroRepositorio generoRepositorio;
    @Inject
    private RolRepositorio rolRepositorio;
    @Inject
    private AreaRepositorio areaRepositorio;

    public ActualizarEmpeladoCommandHandler(
            EmpleadoRepositorio empleadoRepositorio,
            GeneroRepositorio generoRepositorio,
            RolRepositorio rolRepositorio,
            AreaRepositorio areaRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.generoRepositorio = generoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.areaRepositorio = areaRepositorio;
    }

    public void handle(ActualizarEmpleadoCommand command) {
        Empleado empleado = this.empleadoRepositorio.ObtenerPorId(command.getId());

        if (empleado == null) {
            throw new NoEncontradoExcepcion("Empleado");
        }

        Genero genero = this.generoRepositorio.ObtenerPorId(command.getGeneroId());
        if (genero == null) {
            throw new NoEncontradoExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.ObtenerPorId(command.getRolId());
        if (rol == null) {
            throw new NoEncontradoExcepcion("Rol");
        }

        Area area = this.areaRepositorio.ObtenerPorId(command.getAreaId());
        if (area == null) {
            throw new NoEncontradoExcepcion("Área");
        }

        empleado.setNombre(command.getNombre());
        empleado.setApellido(command.getApellido());
        empleado.setContrasena(command.getContrasena());
        empleado.setFoto(command.getFoto());
        empleado.setFechaNacimiento(command.getFechaNacimiento());
        empleado.setDireccion(command.getDireccion());
        empleado.setTelefono(command.getTelefono());
        empleado.setEmail(command.getEmail());
        empleado.setGenero(genero);
        empleado.setRol(rol);
        empleado.setArea(area);

        empleadoRepositorio.Actualizar(empleado);
    }
}
