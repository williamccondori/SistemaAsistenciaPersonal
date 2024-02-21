package com.cajatacna.sistemaasistenciapersonal.dominio.repositorios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;

public interface IAreaRepositorio {
    Area ObtenerPorId(int id);
    ArrayList<Area> ObtenerTodos();
}
