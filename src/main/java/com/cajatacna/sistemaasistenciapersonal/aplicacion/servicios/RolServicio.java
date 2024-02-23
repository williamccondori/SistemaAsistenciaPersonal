package com.cajatacna.sistemaasistenciapersonal.aplicacion.servicios;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.RolModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;

public class RolServicio {

    private final IRolRepositorio rolRepositorio;

    public RolServicio(IRolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }

    public ArrayList<RolModelo> obtenerTodos() {
        ArrayList<RolModelo> modelos = new ArrayList<>();

        ArrayList<Rol> roles = this.rolRepositorio.obtenerTodos();

        roles.forEach(rol -> {
            RolModelo modelo = new RolModelo();
            modelo.setId(rol.getId());
            modelo.setNombre(rol.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
