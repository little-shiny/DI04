package com.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Clase que se implementa como auxiliar para definir estilos que usaremos en nuestra interfaz, mde esta manera está
 * el codigo separado y mas limpio
 */

public class EstiloReserva {
    public static final Color NARANJA_BK = new Color(211, 118, 51);
    public static final Color AZUL_MARINO = new Color(0, 51, 102);
    public static final Color FONDO_CREMA = new Color(252, 248, 232);
    public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 14);

    public static JLabel crearSubtitulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FUENTE_TITULO);
        l.setForeground(AZUL_MARINO);
        l.setBorder(new EmptyBorder(10, 0, 5, 0));
        return l;
    }
}