package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.Connection;
import java.sql.SQLException;

import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.MariaDBExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class AsistenciaRepositorio implements IAsistenciaRepositorio {

    private final Connection conexion;

    public AsistenciaRepositorio() {
        try {
            conexion = Conexion.getConnection();
        } catch (SQLException excpcion) {
            throw new MariaDBExcepcion(excpcion.getMessage());
        }
    }

}
