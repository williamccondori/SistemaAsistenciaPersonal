package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.AreaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;

public class AreaServicio {

    private final IAreaRepositorio areaRepositorio;

    public AreaServicio(IAreaRepositorio areaRepositorio) {
        this.areaRepositorio = areaRepositorio;
    }

    public ArrayList<AreaModelo> obtenerTodos() {
        ArrayList<AreaModelo> modelos = new ArrayList<>();

        ArrayList<Area> areas = this.areaRepositorio.obtenerTodos();

        areas.forEach(area -> {
            AreaModelo modelo = new AreaModelo();
            modelo.setId(area.getId());
            modelo.setNombre(area.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
