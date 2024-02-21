package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;

public interface IRolRepositorio {
    Rol obtenerPorId(int id);
    ArrayList<Rol> obtenerTodos();
}
