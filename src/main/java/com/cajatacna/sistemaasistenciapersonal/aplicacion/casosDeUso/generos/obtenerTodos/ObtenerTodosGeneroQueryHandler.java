package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.generos.obtenerTodos;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.generos.GeneroRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;

public class ObtenerTodosGeneroQueryHandler {

    private final IGeneroRepositorio generoRepositorio;

    public ObtenerTodosGeneroQueryHandler(IGeneroRepositorio generoRepositorio) {
        this.generoRepositorio = generoRepositorio;
    }

    public ArrayList<GeneroRespuestaModelo> handle() {
        ArrayList<GeneroRespuestaModelo> modelos = new ArrayList<>();

        ArrayList<Genero> generos = this.generoRepositorio.obtenerTodos();

        generos.forEach(genero -> {
            GeneroRespuestaModelo modelo = new GeneroRespuestaModelo();
            modelo.setId(genero.getId());
            modelo.setNombre(genero.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
