package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;

public interface IEmpleadoRepositorio {
    void crear(Empleado empleado);

    void actualizar(Empleado empleado);

    void eliminar(Empleado empleado);

    Empleado obtenerPorId(int id);

    Empleado obtenerPorEmail(String email);

    ArrayList<Empleado> obtenerTodos();
}
