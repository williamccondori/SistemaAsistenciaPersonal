package com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos;

import java.sql.Time;
import java.util.Date;

public class AsistenciaModelo {
    private int id;
    private int empleadoId;
    private Date fecha;
    private Time horaEntrada;
    private Time horaSalida;

    private EmpleadoModelo empleado;

    public EmpleadoModelo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoModelo empleado) {
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }
}
