package com.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * He creado este componente para que todos los botones de la empresa BK
 * sean iguales (Consistencia - UD04).
 */
public class BotonBK extends JButton {

    // Colores corporativos de BK
    private Color azulBK = new Color(0, 51, 102);
    private Color azulHover = new Color(0, 102, 204);

    public BotonBK(String texto) {
        super(texto);
        setBackground(azulBK);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(azulHover); }
            public void mouseExited(MouseEvent e) { setBackground(azulBK); }
        });
    }


}