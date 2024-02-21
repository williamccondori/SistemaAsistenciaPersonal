package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.obtenerTodos;

import java.util.ArrayList;
import java.util.Base64;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.empleados.EmpleadoRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;

public class ObtenerTodosEmpleadoQueryHandler {

    private final IEmpleadoRepositorio empleadoRepositorio;

    public ObtenerTodosEmpleadoQueryHandler(IEmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public ArrayList<EmpleadoRespuestaModelo> handle() {
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
}
