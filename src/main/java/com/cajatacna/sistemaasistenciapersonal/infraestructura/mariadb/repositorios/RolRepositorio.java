package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class RolRepositorio implements IRolRepositorio {

    private final Connection conexion;

    public RolRepositorio() {
        try {
            conexion = Conexion.getConnection();
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public Rol obtenerPorId(int id) {
        try {
            String sql = "SELECT * FROM roles WHERE id = ?";

            CallableStatement callableStatement = conexion.prepareCall(sql);
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Rol rol = new Rol();
                rol.setId(resultSet.getInt("id"));
                rol.setNombre(resultSet.getString("nombre"));
                return rol;
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Rol> obtenerTodos() {
        try {
            String sql = "SELECT * FROM roles";

            CallableStatement callableStatement = conexion.prepareCall(sql);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Rol> roles = new ArrayList<Rol>();

            while (resultSet.next()) {
                Rol rol = new Rol();
                rol.setId(resultSet.getInt("id"));
                rol.setNombre(resultSet.getString("nombre"));
                roles.add(rol);
            }

            return roles;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

}
