package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.areas.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;

public class AreaServicio {
    
    private final IAreaRepositorio areaRepositorio;

    public AreaServicio(IAreaRepositorio areaRepositorio) {
        this.areaRepositorio = areaRepositorio;
    }

    public ArrayList<AreaRespuestaModelo> obtenerTodos() {
        ArrayList<AreaRespuestaModelo> modelos = new ArrayList<>();

        ArrayList<Area> areas = this.areaRepositorio.obtenerTodos();

        areas.forEach(area -> {
            AreaRespuestaModelo modelo = new AreaRespuestaModelo();
            modelo.setId(area.getId());
            modelo.setNombre(area.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
