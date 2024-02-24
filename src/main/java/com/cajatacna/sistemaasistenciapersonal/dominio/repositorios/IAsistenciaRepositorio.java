package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.Date;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Asistencia;
import java.util.ArrayList;

public interface IAsistenciaRepositorio {
    Asistencia verificarRegistro(int empleadoId, Date fecha);

    void crear(Asistencia asistencia);
    
    void actualizar(Asistencia asistencia);
    
    Asistencia obtenerPorId(int id);
    
    ArrayList<Asistencia>obtenerTodos();
            
    ArrayList<Asistencia> obtenerPorEmpleado(String criterioBusqueda);
    
    ArrayList<Asistencia> obtenerPorFechas(Date fechaInicio, Date fechaFin);
    
    ArrayList<Asistencia> obtenerPorFechasYEmpleado(int empleadoId, Date fechaInicio, Date fechaFin);
}
