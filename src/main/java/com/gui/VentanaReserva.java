package com.gui;

import com.componentes.BotonBK;
import com.modelo.ReservaBean;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaReserva extends JFrame {
    private JTextField txtNombre, txtTel;
    private JComboBox<String> comboTipo;
    private JSpinner spinPers, spinJornadas;
    private JCheckBox checkHab;
    private JPanel panelExtra;

    public VentanaReserva() {
        setTitle("BK Salón Habana - Registro");
        setSize(450, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20, 30, 20, 30)); // Más margen lateral
        main.setBackground(Color.WHITE);

        // --- SECCIÓN 1: CONTACTO ---
        main.add(crearSubtitulo("1. DATOS DE CONTACTO"));

        txtNombre = crearTextField();
        txtTel = crearTextField();

        main.add(crearEtiquetaIzquierda("Nombre Completo:"));
        main.add(txtNombre);
        main.add(Box.createVerticalStrut(10));
        main.add(crearEtiquetaIzquierda("Teléfono:"));
        main.add(txtTel);

        main.add(Box.createVerticalStrut(20));

        // --- SECCIÓN 2: DETALLES ---
        main.add(crearSubtitulo("2. DETALLES DEL EVENTO"));

        comboTipo = new JComboBox<>(new String[]{"Banquete", "Jornada", "Congreso"});
        ajustarComponente(comboTipo);

        // ajuste spinner más grande y fácil de leer
        spinPers = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        ajustarComponente(spinPers);

        main.add(crearEtiquetaIzquierda("Tipo de Evento:"));
        main.add(comboTipo);
        main.add(Box.createVerticalStrut(10));
        main.add(crearEtiquetaIzquierda("Número de asistentes:"));
        main.add(spinPers);

        main.add(Box.createVerticalStrut(20));

        // --- SECCIÓN 3: componentes adicionales
        main.add(crearSubtitulo("3. OPCIONES DE CONGRESO"));
        panelExtra = new JPanel();
        panelExtra.setLayout(new BoxLayout(panelExtra, BoxLayout.Y_AXIS));
        panelExtra.setBackground(new Color(245, 245, 245));
        panelExtra.setBorder(new EmptyBorder(10, 10, 10, 10));

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
        ajustarComponente(spinJornadas);
        checkHab = new JCheckBox("Se necesitan habitaciones");
        checkHab.setAlignmentX(Component.LEFT_ALIGNMENT);

        spinJornadas.setEnabled(false);
        checkHab.setEnabled(false);

        panelExtra.add(new JLabel("Días del congreso:"));
        panelExtra.add(spinJornadas);
        panelExtra.add(checkHab);
        panelExtra.setMaximumSize(new Dimension(400, 100));
        main.add(panelExtra);

        // Lógica dinámica
        comboTipo.addActionListener(e -> {
            boolean esCongreso = comboTipo.getSelectedItem().equals("Congreso");
            spinJornadas.setEnabled(esCongreso);
            checkHab.setEnabled(esCongreso);
            panelExtra.setBackground(esCongreso ? new Color(230, 245, 255) : new Color(245, 245, 245));
        });

        // --- BOTÓN ---
        main.add(Box.createVerticalStrut(30));
        BotonBK btnConfirmar = new BotonBK("CONFIRMAR RESERVA");
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(btnConfirmar);

        btnConfirmar.addActionListener(e -> {
            if (ValidadorBK.validarCampo(txtNombre) && ValidadorBK.validarTelefono(txtTel)) {
                mostrarResumen();
            } else {
                JOptionPane.showMessageDialog(this, "Datos en color rojo incorrectos");
            }
        });

        add(new JScrollPane(main));
    }


    private JTextField crearTextField() {
        JTextField tf = new JTextField();
        ajustarComponente(tf);
        return tf;
    }

    private void ajustarComponente(JComponent c) {
        // Fijamos un tamaño máximo para que no se estiren demasiado (UD04 - Estética)
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        c.setPreferredSize(new Dimension(200, 35));
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private JLabel crearSubtitulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        l.setForeground(new Color(0, 51, 153));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel crearEtiquetaIzquierda(String texto) {
        JLabel l = new JLabel(texto);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private void mostrarResumen() {
        String resumen = "Reserva de: " + txtNombre.getText() + "\n" +
                "Evento: " + comboTipo.getSelectedItem() + "\n" +
                "Asistentes: " + spinPers.getValue();
        JOptionPane.showMessageDialog(this, resumen, "Resumen BK", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}