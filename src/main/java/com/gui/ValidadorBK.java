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
     * @param campo JTextField que se valida
     * @return true si datos son correctos
     */
    public static boolean validarNombre(JTextField campo) {
        String texto = campo.getText().trim();

        // ^[a-zA-ZáéíóúÁÉÍÓÚñÑ] -> Letras y espacios
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
     * Método que verifica que los comboBox se hayan seleccionado
     * @param combo comboBox que se verifica
     * @return true si está completado
     */
    public static boolean validarCombo(JComboBox<String> combo){
        //Si no hay nada seleccionado en el combobox
        if (combo.getSelectedIndex() == -1 || combo.getSelectedItem() == null){
            combo.setBackground(COLOR_ERROR);
            return false;
        }
        combo.setBackground(COLOR_OK);
        return true;
    }

    /**
     * Método auxiliar que obtiene el mensaje de error segun el fallo
     * @param txtNombre nombre
     * @param txtTel telefono
     * @return error encontrado
     */
    public static String obtenerMensajeError(JTextField txtNombre, JTextField txtTel, JComboBox<String> comboCocina,
                                             JComboBox<String> comboTipo) {
        StringBuilder sb = new StringBuilder("Por favor, corrija los siguientes errores:\n");

        if (!validarNombre(txtNombre)) {
            sb.append("- El nombre debe tener al menos 2 letras y no contener números ni símbolos.\n");
        }
        if (!validarTelefono(txtTel)) {
            sb.append("- El teléfono debe tener exactamente 9 dígitos numéricos.\n");
        }
        if (!validarCombo(comboCocina)){
            sb.append("- Complete los datos sobre el tipo de cocina\n");
        }
        if (!validarCombo(comboTipo)){
            sb.append("- Complete los datos sobre el tipo de evento\n");
        }

        return sb.toString();
    }

}
