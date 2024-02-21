package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.empleados.obtenerTodosEmpleado;

import java.util.ArrayList;

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

            byte[] foto =  empleado.getFoto();
            if (foto != null) {
                String fotoBase64 = new String(foto);
                fotoBase64 = "data:image/png;base64," + fotoBase64;
                modelo.setFoto(fotoBase64);
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
