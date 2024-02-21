package com.cajatacna.sistemaasistenciapersonal.dominio.excepciones;

public class AutenticacionExcepcion extends RuntimeException {
    public AutenticacionExcepcion() {
        super("Credenciales inválidas");
    }
}
