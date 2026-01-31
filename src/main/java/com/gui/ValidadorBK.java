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

    public static boolean validarCampo(JTextField campo) {
        if (campo.getText().trim().isEmpty()) {
            campo.setBackground(COLOR_ERROR);
            return false;
        }
        campo.setBackground(COLOR_OK);
        return true;
    }

    public static boolean validarTelefono(JTextField campo) {
        if (campo.getText().trim().matches("\\d{9}")) {
            campo.setBackground(COLOR_OK);
            return true;
        }
        campo.setBackground(COLOR_ERROR);
        return false;
    }
}
