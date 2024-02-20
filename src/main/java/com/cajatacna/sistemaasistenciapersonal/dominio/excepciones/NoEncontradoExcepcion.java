package com.cajatacna.sistemaasistenciapersonal.dominio.excepciones;

public class NoEncontradoExcepcion extends RuntimeException {
    public NoEncontradoExcepcion(String nombreEntidad) {
        super(String.format("No se encontró la entidad %s", nombreEntidad));
    }
}
