package com.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que valida los datos
 * Separamos la lógica para que el código sea más mantenible y limpio.
 */
public class ValidadorBK {

    // Rojo muy suave para el fondo de los errores
    private static final Color COLOR_ERROR = new Color(255, 230, 230);
    private static final Color COLOR_OK = Color.WHITE;

    /**
     * Comprueba si un campo de texto está vacío.
     */
    public static boolean campoVacio(JTextField campo) {
        if (campo.getText().trim().isEmpty()) {
            campo.setBackground(COLOR_ERROR);
            return true;
        }
        campo.setBackground(COLOR_OK);
        return false;
    }

    /**
     * Comprueba que el teléfono tenga el formato correcto (9 números).
     */
    public static boolean telefonoInvalido(JTextField campo) {
        String valor = campo.getText().trim();
        // Expresión regular: solo números y longitud de 9
        if (!valor.matches("\\d{9}")) {
            campo.setBackground(COLOR_ERROR);
            return true;
        }
        campo.setBackground(COLOR_OK);
        return false;
    }
}