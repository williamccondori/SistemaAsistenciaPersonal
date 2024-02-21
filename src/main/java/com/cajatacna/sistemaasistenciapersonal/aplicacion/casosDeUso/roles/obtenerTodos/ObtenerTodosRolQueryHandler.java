package com.cajatacna.sistemaasistenciapersonal.aplicacion.casosDeUso.roles.obtenerTodos;

import java.util.ArrayList;

import com.cajatacna.sistemaasistenciapersonal.aplicacion.modelos.roles.RolRespuestaModelo;
import com.cajatacna.sistemaasistenciapersonal.dominio.entidades.Rol;
import com.cajatacna.sistemaasistenciapersonal.dominio.repositorios.IRolRepositorio;

public class ObtenerTodosRolQueryHandler {

    private final IRolRepositorio rolRepositorio;

    public ObtenerTodosRolQueryHandler(IRolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }

    public ArrayList<RolRespuestaModelo> handle() {
        ArrayList<RolRespuestaModelo> modelos = new ArrayList<>();

        ArrayList<Rol> roles = this.rolRepositorio.obtenerTodos();

        roles.forEach(rol -> {
            RolRespuestaModelo modelo = new RolRespuestaModelo();
            modelo.setId(rol.getId());
            modelo.setNombre(rol.getNombre());
            modelos.add(modelo);
        });

        return modelos;
    }
}
