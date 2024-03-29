package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;
import com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.conexion.Conexion;

public class EmpleadoRepositorio implements IEmpleadoRepositorio {

    private final Connection conexion;

    public EmpleadoRepositorio() {
        try {
            conexion = Conexion.getConnection();
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public void crear(Empleado empleado) {
        try {
            CallableStatement callableStatement = this.conexion
                    .prepareCall("CALL agregar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            callableStatement.setString(1, empleado.getNombre());
            callableStatement.setString(2, empleado.getApellido());
            callableStatement.setString(3, empleado.getContrasena());
            callableStatement.setBytes(4, empleado.getFoto());
            callableStatement.setString(5, empleado.getFechaNacimiento());
            callableStatement.setInt(6, empleado.getGenero().getId());
            callableStatement.setString(7, empleado.getDireccion());
            callableStatement.setString(8, empleado.getTelefono());
            callableStatement.setString(9, empleado.getEmail());
            callableStatement.setInt(10, empleado.getRol().getId());
            callableStatement.setInt(11, empleado.getArea().getId());

            callableStatement.execute();

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public void actualizar(Empleado empleado) {
        try {
            CallableStatement callableStatement = this.conexion
                    .prepareCall("CALL actualizar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            callableStatement.setInt(1, empleado.getId());
            callableStatement.setString(2, empleado.getNombre());
            callableStatement.setString(3, empleado.getApellido());
            callableStatement.setString(4, empleado.getContrasena());
            callableStatement.setBytes(5, empleado.getFoto());
            callableStatement.setString(6, empleado.getFechaNacimiento());
            callableStatement.setInt(7, empleado.getGenero().getId());
            callableStatement.setString(8, empleado.getDireccion());
            callableStatement.setString(9, empleado.getTelefono());
            callableStatement.setString(10, empleado.getEmail());
            callableStatement.setInt(11, empleado.getRol().getId());
            callableStatement.setInt(12, empleado.getArea().getId());

            callableStatement.execute();
        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public void eliminar(Empleado empleado) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL eliminar_empleado(?)");
            callableStatement.setInt(1, empleado.getId());

            callableStatement.execute();

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public Empleado obtenerPorId(int id) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL buscar_empleado_por_id(?)");
            callableStatement.setInt(1, id);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                return this.mapearUsuario(resultSet, true);
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public Empleado obtenerPorCredenciales(String email, String contrasena) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL login_empleado(?, ?)");
            callableStatement.setString(1, email);
            callableStatement.setString(2, contrasena);

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                return this.mapearUsuario(resultSet, false);
            } else {
                return null;
            }

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    @Override
    public ArrayList<Empleado> obtenerTodos(String criterioBusqueda) {
        try {
            CallableStatement callableStatement = this.conexion.prepareCall("CALL BuscarEmpleados(?)");
            callableStatement.setString(1, criterioBusqueda);

            ResultSet resultSet = callableStatement.executeQuery();

            ArrayList<Empleado> empleados = new ArrayList<>();

            while (resultSet.next()) {
                empleados.add(this.mapearUsuario(resultSet, true));
            }

            return empleados;

        } catch (SQLException excpcion) {
            throw new AplicacionExcepcion(excpcion.getMessage());
        }
    }

    private Empleado mapearUsuario(ResultSet resultSet, boolean incluirPadres) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(resultSet.getInt("id"));
        empleado.setNombre(resultSet.getString("nombre"));
        empleado.setApellido(resultSet.getString("apellido"));
        empleado.setContrasena(resultSet.getString("contrasena"));
        empleado.setFoto(resultSet.getBytes("foto"));
        empleado.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
        empleado.setDireccion(resultSet.getString("direccion"));
        empleado.setTelefono(resultSet.getString("telefono"));
        empleado.setEmail(resultSet.getString("email"));

        Rol rol = new Rol();
        rol.setId(resultSet.getInt("rol_id"));
        Area area = new Area();
        area.setId(resultSet.getInt("area_id"));
        Genero genero = new Genero();
        genero.setId(resultSet.getInt("genero_id"));

        if (incluirPadres) {
            rol.setNombre(resultSet.getString("rol"));
            area.setNombre(resultSet.getString("area"));
            genero.setNombre(resultSet.getString("genero"));
        }

        empleado.setRol(rol);
        empleado.setArea(area);
        empleado.setGenero(genero);

        return empleado;
    }

}
