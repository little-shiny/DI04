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
    private final Color azulNormal = new Color(0, 102, 204);
    private final Color azulClaro = new Color(51, 153, 255);

    public BotonBK(String texto) {
        super(texto);

        // Estilo visual inicial
        setBackground(azulNormal);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setFocusPainted(false); // Quita el recuadro feo al hacer clic
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // EFECTO HOVER: Para que el usuario sepa que puede interactuar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(azulClaro); // Se ilumina al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(azulNormal); // Vuelve al color original
            }
        });
    }
}