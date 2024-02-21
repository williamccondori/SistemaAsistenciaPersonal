package com.cajatacna.sistemaasistenciapersonal.dominio.excepciones;

public class MariaDBExcepcion extends RuntimeException {
    public MariaDBExcepcion(String mensaje) {
        super(mensaje);
    }
}
