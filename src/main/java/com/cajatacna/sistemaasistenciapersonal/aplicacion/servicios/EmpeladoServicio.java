package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;
import java.util.Base64;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoActualizarModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
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

    public EmpleadoModelo autenticar(String email, String contrasena) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorCredenciales(email, contrasena);

        if (empleado == null) {
            throw new AplicacionExcepcion("No se encontró el empleado");
        }

        EmpleadoModelo modelo = new EmpleadoModelo();
        modelo.setId(empleado.getId());
        modelo.setEmail(empleado.getEmail());

        return modelo;
    }

    public EmpleadoModelo obtenerPorId(int empleadoId) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorId(empleadoId);

        if (empleado == null) {
            return null;
        }

        EmpleadoModelo modelo = new EmpleadoModelo();
        modelo.setId(empleado.getId());

        byte[] foto = empleado.getFoto();
        if (foto != null) {
            modelo.setFotoBase64(Base64.getEncoder().encodeToString(foto));
        }

        modelo.setNombre(empleado.getNombre());
        modelo.setApellido(empleado.getApellido());
        modelo.setRol(empleado.getRol().getNombre());
        modelo.setArea(empleado.getArea().getNombre());

        return modelo;
    }

    public ArrayList<EmpleadoModelo> obtenerTodos() {
        ArrayList<EmpleadoModelo> modelos = new ArrayList<>();

        ArrayList<Empleado> empleados = this.empleadoRepositorio.obtenerTodos("");

        empleados.forEach(empleado -> {
            EmpleadoModelo modelo = new EmpleadoModelo();
            modelo.setId(empleado.getId());

            byte[] foto = empleado.getFoto();
            if (foto != null) {
                modelo.setFotoBase64(Base64.getEncoder().encodeToString(foto));
            }

            modelo.setNombre(empleado.getNombre());
            modelo.setApellido(empleado.getApellido());
            modelo.setRol(empleado.getRol().getNombre());
            modelo.setArea(empleado.getArea().getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }

    public void crear(EmpleadoModelo empleadoModelo) {
        Genero genero = this.generoRepositorio.obtenerPorId(empleadoModelo.getGeneroId());
        if (genero == null) {
            throw new AplicacionExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.obtenerPorId(empleadoModelo.getRolId());
        if (rol == null) {
            throw new AplicacionExcepcion("Rol");
        }

        Area area = this.areaRepositorio.obtenerPorId(empleadoModelo.getAreaId());
        if (area == null) {
            throw new AplicacionExcepcion("Área");
        }

        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoModelo.getNombre());
        empleado.setApellido(empleadoModelo.getApellido());
        empleado.setContrasena(empleadoModelo.getContrasena());
        empleado.setFoto(empleadoModelo.getFoto());
        empleado.setFechaNacimiento(empleadoModelo.getFechaNacimiento());
        empleado.setDireccion(empleadoModelo.getDireccion());
        empleado.setTelefono(empleadoModelo.getTelefono());
        empleado.setEmail(empleadoModelo.getEmail());
        empleado.setGenero(genero);
        empleado.setRol(rol);
        empleado.setArea(area);

        empleadoRepositorio.crear(empleado);
    }

    public void actualizar(EmpleadoActualizarModelo empleadoModelo) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorId(empleadoModelo.getId());

        if (empleado == null) {
            throw new AplicacionExcepcion("Empleado");
        }

        Genero genero = this.generoRepositorio.obtenerPorId(empleadoModelo.getGeneroId());
        if (genero == null) {
            throw new AplicacionExcepcion("Género");
        }

        Rol rol = this.rolRepositorio.obtenerPorId(empleadoModelo.getRolId());
        if (rol == null) {
            throw new AplicacionExcepcion("Rol");
        }

        Area area = this.areaRepositorio.obtenerPorId(empleadoModelo.getAreaId());
        if (area == null) {
            throw new AplicacionExcepcion("Área");
        }

        empleado.setNombre(empleadoModelo.getNombre());
        empleado.setApellido(empleadoModelo.getApellido());
        empleado.setContrasena(empleadoModelo.getContrasena());
        empleado.setFoto(empleadoModelo.getFoto());
        empleado.setFechaNacimiento(empleadoModelo.getFechaNacimiento());
        empleado.setDireccion(empleadoModelo.getDireccion());
        empleado.setTelefono(empleadoModelo.getTelefono());
        empleado.setEmail(empleadoModelo.getEmail());
        empleado.setGenero(genero);
        empleado.setRol(rol);
        empleado.setArea(area);

        empleadoRepositorio.actualizar(empleado);
    }
}
