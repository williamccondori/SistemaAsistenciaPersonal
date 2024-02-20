package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Empleado;

public interface EmpleadoRepositorio {
    void Crear(Empleado empleado);

    void Actualizar(Empleado empleado);

    void Eliminar(Empleado empleado);

    Empleado ObtenerPorId(int id);

    Empleado ObtenerPorUsuario(String usuario);

    ArrayList<Empleado> ObtenerTodos();
}
