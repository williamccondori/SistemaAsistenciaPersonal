package com.cajatacna.sistemaasistenciapersonal.aplicacion.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import com.cajatacna.sistemaasistenciapersonal.dominio.excepciones.AplicacionExcepcion;

public class Utilidades {

    public static byte[] obtenerBytesDePart(Part archivoPart) throws IOException {

        if (archivoPart != null) {
            byte[] bytesArchivo;

            try (InputStream inputStream = archivoPart.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }

                // Valida el tamaño del archivo sea menor a 1MB.
                if (baos.size() > 1024 * 1024) {
                    throw new AplicacionExcepcion("El tamaño del archivo no debe superar 1MB");
                }

                bytesArchivo = baos.toByteArray();
            }

            return bytesArchivo;
        }

        return null;
    }

    public static int obtenerInt(String valor) {
        return valor == null ? 0 : Integer.parseInt(valor);
    }

}
