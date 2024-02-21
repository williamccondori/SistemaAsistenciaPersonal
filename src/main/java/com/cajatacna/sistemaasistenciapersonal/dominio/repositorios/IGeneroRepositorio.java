package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;

public interface IGeneroRepositorio {
    Genero ObtenerPorId(int id);
    ArrayList<Genero> ObtenerTodos();
}
