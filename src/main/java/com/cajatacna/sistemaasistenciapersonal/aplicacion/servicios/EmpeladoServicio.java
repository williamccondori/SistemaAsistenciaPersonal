package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;
import java.util.Base64;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.actualizarEmpleado.ActualizarEmpleadoCommand;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.crearEmpleado.CrearEmpleadoCommand;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.empleados.EmpleadoRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;

public class EmpeladoServicio {
    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IGeneroRepositorio generoRepositorio;
    private final IRolRepositorio rolRepositorio;
    private final IAreaRepositorio areaRepositorio;

    public EmpeladoServicio(
            IEmpleadoRepositorio empleadoRepositorio,
            IGeneroRepositorio generoRepositorio,
            IRolRepositorio rolRepositorio,
            IAreaRepositorio areaRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.generoRepositorio = generoRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.areaRepositorio = areaRepositorio;
    }

    public ArrayList<EmpleadoRespuestaModelo> obtenerTodos() {
        ArrayList<EmpleadoRespuestaModelo> modelos = new ArrayList<>();

        ArrayList<Empleado> empleados = this.empleadoRepositorio.obtenerTodos("");

        empleados.forEach(empleado -> {
            EmpleadoRespuestaModelo modelo = new EmpleadoRespuestaModelo();
            modelo.setId(empleado.getId());

            byte[] foto = empleado.getFoto();
            if (foto != null) {
                modelo.setFoto(Base64.getEncoder().encodeToString(foto));
            }

            modelo.setNombre(empleado.getNombre());
            modelo.setApellido(empleado.getApellido());
            modelo.setRol(empleado.getRol().getNombre());
            modelo.setArea(empleado.getArea().getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }

    public void crear(CrearEmpleadoCommand command) {
        Genero genero = this.generoRepositorio.obtenerPorId(command.getGeneroId());
        if (genero == null) {
            throw new AplicacionExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.obtenerPorId(command.getRolId());
        if (rol == null) {
            throw new AplicacionExcepcion("Rol");
        }

        Area area = this.areaRepositorio.obtenerPorId(command.getAreaId());
        if (area == null) {
            throw new AplicacionExcepcion("Área");
        }

        Empleado empleado = new Empleado();
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

        empleadoRepositorio.crear(empleado);
    }

    public void actualizar(ActualizarEmpleadoCommand command) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorId(command.getId());

        if (empleado == null) {
            throw new AplicacionExcepcion("Empleado");
        }

        Genero genero = this.generoRepositorio.obtenerPorId(command.getGeneroId());
        if (genero == null) {
            throw new AplicacionExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.obtenerPorId(command.getRolId());
        if (rol == null) {
            throw new AplicacionExcepcion("Rol");
        }

        Area area = this.areaRepositorio.obtenerPorId(command.getAreaId());
        if (area == null) {
            throw new AplicacionExcepcion("Área");
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
