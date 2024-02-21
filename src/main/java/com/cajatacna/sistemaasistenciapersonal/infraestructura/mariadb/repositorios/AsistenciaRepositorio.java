package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.Connection;

import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAsistenciaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class AsistenciaRepositorio implements IAsistenciaRepositorio {

    private final Connection conexion;

    public AsistenciaRepositorio() {
        conexion = Conexion.getConnection();
    }

}
