package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.actualizarEmpleado;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.NoEncontradoExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;

public class ActualizarEmpeladoCommandHandler {

    private IEmpleadoRepositorio empleadoRepositorio;
    private IGeneroRepositorio generoRepositorio;
    private IRolRepositorio rolRepositorio;
    private IAreaRepositorio areaRepositorio;

    public ActualizarEmpeladoCommandHandler(
            IEmpleadoRepositorio empleadoRepositorio,
            IGeneroRepositorio generoRepositorio,
            IRolRepositorio rolRepositorio,
            IAreaRepositorio areaRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.generoRepositorio = generoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.areaRepositorio = areaRepositorio;
    }

    public void handle(ActualizarEmpleadoCommand command) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorId(command.getId());

        if (empleado == null) {
            throw new NoEncontradoExcepcion("Empleado");
        }

        Genero genero = this.generoRepositorio.obtenerPorId(command.getGeneroId());
        if (genero == null) {
            throw new NoEncontradoExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.obtenerPorId(command.getRolId());
        if (rol == null) {
            throw new NoEncontradoExcepcion("Rol");
        }

        Area area = this.areaRepositorio.obtenerPorId(command.getAreaId());
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

        empleadoRepositorio.actualizar(empleado);
    }
}
