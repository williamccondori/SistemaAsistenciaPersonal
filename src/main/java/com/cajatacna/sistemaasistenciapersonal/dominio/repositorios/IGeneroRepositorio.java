package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;

public interface IGeneroRepositorio {
    Genero obtenerPorId(int id);
    ArrayList<Genero> obtenerTodos();
}
