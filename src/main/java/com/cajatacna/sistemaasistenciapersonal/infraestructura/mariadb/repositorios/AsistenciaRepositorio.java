package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Asistencia;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;
import java.util.ArrayList;

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
                    .prepareCall("CALL agregar_asistencia(?, ?, ?, ?)");
            callableStatement.setInt(1, asistencia.getEmpleado().getId());

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaSql = new java.sql.Date(asistencia.getFecha().getTime());
            callableStatement.setDate(2, fechaSql);

            callableStatement.setTime(3, asistencia.getHoraEntrada());
            callableStatement.setTime(4, asistencia.getHoraSalida());

            callableStatement.execute();

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public void actualizar(Asistencia asistencia) {
        try {
            CallableStatement callableStatement = this.conexion
                    .prepareCall("CALL actualizar_asistencia(?, ?, ?, ?, ?)");
            callableStatement.setInt(1, asistencia.getId());
            callableStatement.setInt(2, asistencia.getEmpleado().getId());

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaSql = new java.sql.Date(asistencia.getFecha().getTime());
            callableStatement.setDate(3, fechaSql);

            callableStatement.setTime(4, asistencia.getHoraEntrada());
            callableStatement.setTime(5, asistencia.getHoraSalida());

            callableStatement.execute();
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public Asistencia verificarRegistro(int empleadoId, Date fecha) {
        try {
            CallableStatement callableStatement = this.conexion
                    .prepareCall("CALL VerificarRegistroEmpleado(?,?)");
            callableStatement.setInt(1, empleadoId);

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
            callableStatement.setDate(2, fechaSql);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));

                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                return asistencia;
            } else {
                return null;
            }
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public Asistencia obtenerPorId(int id) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL buscar_asistencia_por_id(?)");
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));

                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                return asistencia;
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Asistencia> obtenerTodos() {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL listar_asistencias()");

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Asistencia> asistencias = new ArrayList<>();

            while (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                asistencias.add(asistencia);
            }

            return asistencias;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Asistencia> obtenerPorEmpleado(String criterioBusqueda) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL reporte_asistencia_por_empleado(?)");
            callableStatement.setString(1, criterioBusqueda);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Asistencia> asistencias = new ArrayList<>();

            while (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                asistencias.add(asistencia);
            }

            return asistencias;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Asistencia> obtenerPorFechas(Date fechaInicio, Date fechaFin) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL reporte_asistencia_por_fechas(?,?)");

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaISql = new java.sql.Date(fechaInicio.getTime());
            callableStatement.setDate(1, fechaISql);

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaFSql = new java.sql.Date(fechaFin.getTime());
            callableStatement.setDate(2, fechaFSql);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Asistencia> asistencias = new ArrayList<>();

            while (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                asistencias.add(asistencia);
            }

            return asistencias;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Asistencia> obtenerPorFechasYEmpleado(int empleadoId, Date fechaInicio, Date fechaFin) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL reporte_asistencia_por_fechas_y_empleado(?,?,?)");
            callableStatement.setInt(1, empleadoId);

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaISql = new java.sql.Date(fechaInicio.getTime());
            callableStatement.setDate(2, fechaISql);

            // Transformar java.util.Date a java.sql.Date.
            java.sql.Date fechaFSql = new java.sql.Date(fechaFin.getTime());
            callableStatement.setDate(3, fechaFSql);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Asistencia> asistencias = new ArrayList<>();

            while (resultSet.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(resultSet.getInt("id"));
                asistencia.setFecha(resultSet.getDate("fecha"));
                asistencia.setHoraEntrada(resultSet.getTime("hora_entrada"));
                asistencia.setHoraSalida(resultSet.getTime("hora_salida"));
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("empleado_id"));
                asistencia.setEmpleado(empleado);

                asistencias.add(asistencia);
            }

            return asistencias;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }
}
