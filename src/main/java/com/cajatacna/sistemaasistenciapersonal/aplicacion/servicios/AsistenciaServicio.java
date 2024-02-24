package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;
import java.util.Date;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AsistenciaModelo;
import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.EmpleadoModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Asistencia;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;

public class AsistenciaServicio {

    private final IEmpleadoRepositorio empleadoRepositorio;
    private final IAsistenciaRepositorio asistenciaRepositorio;

    public AsistenciaServicio(
            IEmpleadoRepositorio empleadoRepositorio,
            IAsistenciaRepositorio asistenciaRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.asistenciaRepositorio = asistenciaRepositorio;
    }

    public AsistenciaModelo verificarRegistro(int empleadoId) {
        Date fechaActual = new Date();
        Asistencia asistencia = this.asistenciaRepositorio.verificarRegistro(empleadoId, fechaActual);
        if (asistencia == null) {
            return null;
        }

        AsistenciaModelo modelo = new AsistenciaModelo();
        modelo.setId(asistencia.getId());
        modelo.setEmpleadoId(asistencia.getEmpleado().getId());
        modelo.setFecha(asistencia.getFecha());
        modelo.setHoraEntrada(asistencia.getHoraEntrada());
        modelo.setHoraSalida(asistencia.getHoraSalida());
        return modelo;
    }

    public void registrar(AsistenciaModelo modelo) {
        Empleado empleado = this.empleadoRepositorio.obtenerPorId(modelo.getEmpleadoId());
        if (empleado == null) {
            throw new AplicacionExcepcion("No se encontró el empleado");
        }

        if (modelo.getId() == 0) {
            // Si no tiene ID es creación.
            Asistencia asistencia = new Asistencia();
            asistencia.setEmpleado(empleado);
            asistencia.setFecha(modelo.getFecha());
            asistencia.setHoraEntrada(modelo.getHoraEntrada());

            this.asistenciaRepositorio.crear(asistencia);
        } else {
            Asistencia asistencia = this.asistenciaRepositorio.obtenerPorId(modelo.getId());

            if (asistencia.getHoraSalida() != null) {
                throw new AplicacionExcepcion("Ya marcó asistencia por el día de hoy");
            }

            asistencia.setHoraSalida(modelo.getHoraSalida());
            this.asistenciaRepositorio.actualizar(asistencia);
        }
    }

    public ArrayList<AsistenciaModelo> obtenerPorFechasYEmpleado(int empleadoId, Date fechaInicio, Date fechaFin) {
        ArrayList<Asistencia> asistencias = this.asistenciaRepositorio.obtenerPorFechasYEmpleado(empleadoId,
                fechaInicio, fechaFin);

        ArrayList<AsistenciaModelo> modelos = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            AsistenciaModelo modelo = new AsistenciaModelo();
            modelo.setId(asistencia.getId());
            modelo.setEmpleadoId(asistencia.getEmpleado().getId());
            modelo.setFecha(asistencia.getFecha());
            modelo.setHoraEntrada(asistencia.getHoraEntrada());
            modelo.setHoraSalida(asistencia.getHoraSalida());
            modelos.add(modelo);
        }

        return modelos;
    }

    public ArrayList<AsistenciaModelo> obtenerPorFechas(Date fechaInicio, Date fechaFin) {
        ArrayList<Asistencia> asistencias = this.asistenciaRepositorio.obtenerPorFechas(fechaInicio, fechaFin);

        ArrayList<AsistenciaModelo> modelos = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            AsistenciaModelo modelo = new AsistenciaModelo();
            modelo.setId(asistencia.getId());
            modelo.setEmpleadoId(asistencia.getEmpleado().getId());
            modelo.setFecha(asistencia.getFecha());
            modelo.setHoraEntrada(asistencia.getHoraEntrada());
            modelo.setHoraSalida(asistencia.getHoraSalida());

            Empleado empleado = this.empleadoRepositorio.obtenerPorId(asistencia.getEmpleado().getId());
            EmpleadoModelo empleadoModelo = new EmpleadoModelo();
            empleadoModelo.setId(empleado.getId());
            empleadoModelo.setNombre(empleado.getNombre());
            empleadoModelo.setApellido(empleado.getApellido());
            empleadoModelo.setEmail(empleado.getEmail());
            empleadoModelo.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoModelo.setDireccion(empleado.getDireccion());
            empleadoModelo.setTelefono(empleado.getTelefono());
            empleadoModelo.setRolId(empleado.getRol().getId());
            empleadoModelo.setAreaId(empleado.getArea().getId());
            empleadoModelo.setGeneroId(empleado.getGenero().getId());
            empleadoModelo.setRol(empleado.getRol().getNombre());
            empleadoModelo.setArea(empleado.getArea().getNombre());
            empleadoModelo.setGenero(empleado.getGenero().getNombre());
            modelo.setEmpleado(empleadoModelo);

            modelos.add(modelo);
        }

        return modelos;
    }
}
