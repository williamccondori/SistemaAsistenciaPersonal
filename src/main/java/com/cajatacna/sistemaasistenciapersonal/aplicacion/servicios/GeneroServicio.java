package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.GeneroModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Genero;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IGeneroRepositorio;

public class GeneroServicio {

    private final IGeneroRepositorio generoRepositorio;

    public GeneroServicio(IGeneroRepositorio generoRepositorio) {
        this.generoRepositorio = generoRepositorio;
    }

    public ArrayList<GeneroModelo> obtenerTodos() {
        ArrayList<GeneroModelo> modelos = new ArrayList<>();

        ArrayList<Genero> generos = this.generoRepositorio.obtenerTodos();

        generos.forEach(genero -> {
            GeneroModelo modelo = new GeneroModelo();
            modelo.setId(genero.getId());
            modelo.setNombre(genero.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
