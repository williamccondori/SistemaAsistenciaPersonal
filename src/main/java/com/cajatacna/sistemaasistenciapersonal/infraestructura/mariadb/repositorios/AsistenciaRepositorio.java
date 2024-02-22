package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Asistencia;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class AsistenciaRepositorio implements IAsistenciaRepositorio {

    private final Connection conexion;

    public AsistenciaRepositorio() {
        try {
            conexion = Conexion.getConnection();
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public void crear(Asistencia asistencia) {
        try {
            CallableStatement callableStatement = this.conexion
                    .prepareCall("CALL agregar_asistencia(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            callableStatement.setInt(1, asistencia.getEmpleado().getId());
            callableStatement.setDate(2, (Date) asistencia.getFecha());
            callableStatement.setTime(3, asistencia.getHoraEntrada());
            callableStatement.setTime(4, asistencia.getHoraSalida());

            callableStatement.execute();

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

}
