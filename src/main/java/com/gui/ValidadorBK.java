package com.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que valida los datos
 * Separamos la lógica para que el código sea más mantenible y limpio.
 */
public class ValidadorBK {
    private static final Color COLOR_ERROR = new Color(255, 230, 230);
    private static final Color COLOR_OK = Color.WHITE;

    /**
     * Valida el nombre.
     * - más de 1 caracter
     * - solo letras (espacios, tildes y ñ)
     * - no numeros ni simbolos
     * @param campo
     * @return true si datos son correctos
     */
    public static boolean validarNombre(JTextField campo) {
        String texto = campo.getText().trim();

        // ^[a-zA-ZáéíóúÁÉÍÓÚñÑ ] -> Letras y espacios
        // {2,} -> Mínimo 2 caracteres
        String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,}$";

        if (texto.matches(regexNombre)) {
            campo.setBackground(COLOR_OK);
            return true;
        } else {
            campo.setBackground(COLOR_ERROR);
            return false;
        }
    }

    /**
     * Valida el teléfono: 9 dígitos numéricos exactos.
     */
    public static boolean validarTelefono(JTextField campo) {
        String texto = campo.getText().trim();

        if (texto.matches("\\d{9}")) {
            campo.setBackground(COLOR_OK);
            return true;
        } else {
            campo.setBackground(COLOR_ERROR);
            return false;
        }
    }

    /**
     * Método auxiliar que obtiene el mensaje de error segun el fallo
     * @param txtNombre nombre
     * @param txtTel telefono
     * @return error encontrado
     */
    public static String obtenerMensajeError(JTextField txtNombre, JTextField txtTel) {
        StringBuilder sb = new StringBuilder("Por favor, corrija los siguientes errores:\n");

        if (!validarNombre(txtNombre)) {
            sb.append("- El nombre debe tener al menos 2 letras y no contener números ni símbolos.\n");
        }
        if (!validarTelefono(txtTel)) {
            sb.append("- El teléfono debe tener exactamente 9 dígitos numéricos.\n");
        }

        return sb.toString();
    }

}
