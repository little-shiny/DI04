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
    // Matiz para bordes (un poco más oscuro que el fondo para dar relieve)
    public static final Color BORDE_MATIZ = new Color(220, 210, 190);

    // Fuentes
    public static final Font FUENTE_LABEL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 15);

    public static JLabel crearSubtitulo(String texto) {
        JLabel l = new JLabel(texto.toUpperCase());
        l.setFont(FUENTE_TITULO);
        l.setForeground(AZUL_MARINO);
        l.setBorder(new EmptyBorder(15, 0, 8, 0));
        return l;
    }
}