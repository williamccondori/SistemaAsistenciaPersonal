package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.MariaDBExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class AreaRepositorio implements IAreaRepositorio {

    private final Connection conexion;

    public AreaRepositorio() {
        conexion = Conexion.getConnection();
    }

    @Override
    public Area obtenerPorId(int id) {
        try {
            String sql = "SELECT * FROM area WHERE id = ?";

            CallableStatement callableStatement = conexion.prepareCall(sql);
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Area area = new Area();
                area.setId(resultSet.getInt("id"));
                area.setNombre(resultSet.getString("nombre"));
                return area;
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new MariaDBExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Area> obtenerTodos() {
        try {
            String sql = "SELECT * FROM area";

            CallableStatement callableStatement = conexion.prepareCall(sql);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Area> areas = new ArrayList<Area>();

            while (resultSet.next()) {
                Area area = new Area();
                area.setId(resultSet.getInt("id"));
                area.setNombre(resultSet.getString("nombre"));
                areas.add(area);
            }

            return areas;

        } catch (SQLException excpcion) {
            throw new MariaDBExcepcion(excpcion.getMessage());
        }
    }

}
