package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;

public interface IRolRepositorio {
    Rol ObtenerPorId(int id);
    ArrayList<Rol> ObtenerTodos();
}
