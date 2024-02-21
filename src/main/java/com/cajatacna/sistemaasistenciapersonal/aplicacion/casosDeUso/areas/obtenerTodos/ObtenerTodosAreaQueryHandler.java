package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.areas.obtenerTodos;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.areas.AreaRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Area;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IAreaRepositorio;

public class ObtenerTodosAreaQueryHandler {

    private final IAreaRepositorio areaRepositorio;

    public ObtenerTodosAreaQueryHandler(IAreaRepositorio areaRepositorio) {
        this.areaRepositorio = areaRepositorio;
    }

    public ArrayList<AreaRespuestaModelo> handle() {
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
