package com.cajatacna.sistemaasistenciapersonal.dominio.excepciones;

public class AplicacionExcepcion extends RuntimeException {
    public AplicacionExcepcion(String mensaje) {
        super(String.format(mensaje));
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
