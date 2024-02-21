package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.MariaDBExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class GeneroRepositorio implements IGeneroRepositorio {

    private final Connection conexion;

    public GeneroRepositorio() {
        conexion = Conexion.getConnection();
    }

    @Override
    public Genero obtenerPorId(int id) {
        try {
            String sql = "SELECT * FROM genero WHERE id = ?";

            CallableStatement callableStatement = conexion.prepareCall(sql);
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Genero genero = new Genero();
                genero.setId(resultSet.getInt("id"));
                genero.setNombre(resultSet.getString("nombre"));
                return genero;
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new MariaDBExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Genero> obtenerTodos() {
        try {
            String sql = "SELECT * FROM genero";

            CallableStatement callableStatement = conexion.prepareCall(sql);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Genero> generos = new ArrayList<Genero>();

            while (resultSet.next()) {
                Genero genero = new Genero();
                genero.setId(resultSet.getInt("id"));
                genero.setNombre(resultSet.getString("nombre"));
                generos.add(genero);
            }

            return generos;

        } catch (SQLException excpcion) {
            throw new MariaDBExcepcion(excpcion.getMessage());
        }
    }

}
