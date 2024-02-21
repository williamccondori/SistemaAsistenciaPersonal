package com.cajatacna.sistemaasistenciapersonal.infraestructura.mariadb.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IEmpleadoRepositorio;

public class EmpleadoRepositorio implements IEmpleadoRepositorio {

    @Override
    public void crear(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Crear'");
    }

    @Override
    public void actualizar(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Actualizar'");
    }

    @Override
    public void eliminar(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Eliminar'");
    }

    @Override
    public Empleado obtenerPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ObtenerPorId'");
    }

    @Override
    public Empleado obtenerPorEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ObtenerPorUsuario'");
    }

    @Override
    public ArrayList<Empleado> obtenerTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ObtenerTodos'");
    }

}
