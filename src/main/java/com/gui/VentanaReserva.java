package com.gui;

import com.componentes.BotonBK;
import com.modelo.ReservaBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VentanaReserva extends JFrame {
    private JTextField txtNombre, txtTel;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboCocina;
    private JSpinner spinPers, spinJornadas;
    private JCheckBox checkHab;
    private JPanel panelExtra;

    public VentanaReserva() {
        setTitle("BK Salón Habana - Formulario de Reserva");
        setSize(480, 780);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con scroll
        JPanel main = new JPanel(new GridBagLayout());
        // USO DE ESTILO: Fondo crema corporativo
        main.setBackground(EstiloReserva.FONDO_CREMA);
        main.setBorder(new EmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración común para los componentes
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // --- APORTACIÓN ORIGINAL: LOGO BK (Identidad Visual UD04) ---
        gbc.gridy = 0; gbc.gridx = 0; gbc.gridwidth = 2;
        JLabel lblLogo = new JLabel("<html><div style='text-align:center;'><h1 style='color:#D37633; font-size:35px; font-family:Arial;'>BK</h1><p style='color:#003366; margin-top:-10px; font-weight:bold;'>SALÓN HABANA</p></div></html>");
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setBorder(new EmptyBorder(0, 0, 15, 0));
        main.add(lblLogo, gbc);

        // --- SECCIÓN 1: CONTACTO ---
        gbc.gridy = 1;
        // USO DE ESTILO: Subtítulo usando el método de la clase EstiloReserva
        main.add(EstiloReserva.crearSubtitulo("1. DATOS DE CONTACTO"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2; gbc.gridx = 0;
        main.add(new JLabel("Nombre Completo:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        main.add(txtNombre, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        main.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        txtTel = new JTextField(15);
        main.add(txtTel, gbc);

        // --- SECCIÓN 2: DETALLES ---
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        main.add(EstiloReserva.crearSubtitulo("2. DETALLES DEL EVENTO"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 5; gbc.gridx = 0;
        main.add(new JLabel("Tipo de Evento:"), gbc);

        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Banquete", "Jornada", "Congreso"});
        comboTipo.setSelectedIndex(-1);
        main.add(comboTipo, gbc);

        gbc.gridy = 6; gbc.gridx = 0;
        main.add(new JLabel("Nº Asistentes:"), gbc);

        gbc.gridx = 1;
        spinPers = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        spinPers.setPreferredSize(new Dimension(80, 25));
        main.add(spinPers, gbc);

        // Campo cocina
        gbc.gridy = 7; gbc.gridx = 0;
        main.add(new JLabel("Tipo de cocina:"), gbc);
        gbc.gridx = 1;
        String[] opcionesCocina = {"Bufé", "Carta", "Pedir cita con el chef", "No precisa"};
        comboCocina = new JComboBox<>(opcionesCocina);
        comboCocina.setSelectedIndex(-1);
        main.add(comboCocina, gbc);

        // --- SECCIÓN 3 extras
        gbc.gridy = 8; gbc.gridx = 0; gbc.gridwidth = 2;
        panelExtra = new JPanel(new GridBagLayout());
        panelExtra.setBackground(Color.WHITE);
        TitledBorder border = BorderFactory.createTitledBorder("Opciones Congreso");
        // USO DE ESTILO: Color azul marino para el título del borde
        border.setTitleColor(EstiloReserva.AZUL_MARINO);
        panelExtra.setBorder(border);

        GridBagConstraints gbcExtra = new GridBagConstraints();
        gbcExtra.insets = new Insets(5, 5, 5, 5);
        gbcExtra.anchor = GridBagConstraints.WEST;

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinJornadas.setEnabled(false);
        checkHab = new JCheckBox("Habitaciones");
        checkHab.setBackground(Color.WHITE);
        checkHab.setEnabled(false);

        gbcExtra.gridx = 0; gbcExtra.gridy = 0;
        panelExtra.add(new JLabel("Jornadas:"), gbcExtra);
        gbcExtra.gridx = 1;
        panelExtra.add(spinJornadas, gbcExtra);
        gbcExtra.gridx = 0; gbcExtra.gridy = 1; gbcExtra.gridwidth = 2;
        panelExtra.add(checkHab, gbcExtra);

        main.add(panelExtra, gbc);

        // Lógica de activación
        comboTipo.addActionListener(e -> {
            boolean esCongreso = "Congreso".equals(comboTipo.getSelectedItem());
            spinJornadas.setEnabled(esCongreso);
            checkHab.setEnabled(esCongreso);
            panelExtra.setBackground(esCongreso ? new Color(235, 245, 255) : Color.WHITE);
        });

        // --- BOTÓN ---
        gbc.gridy = 9; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);

        BotonBK btn = new BotonBK("CONFIRMAR");

        // forzar estilo porque si no sale blanco
        btn.setOpaque(true);
        btn.setBorderPainted(false); // A veces el borde del sistema oculta el color
        btn.setBackground(EstiloReserva.NARANJA_BK);
        btn.setForeground(Color.WHITE); // Texto en blanco
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(150, 40)); // Le damos un tamaño más grande

        main.add(btn, gbc);

        btn.addActionListener(e -> {
            boolean nombreValido = ValidadorBK.validarNombre(txtNombre);
            boolean telValido = ValidadorBK.validarTelefono(txtTel);
            boolean cocinaOk = ValidadorBK.validarCombo(comboCocina);
            boolean tipoOk = ValidadorBK.validarCombo(comboTipo);

            if (nombreValido && telValido && cocinaOk && tipoOk) {
                ReservaBean reservaBean = rellenarBean();
                JOptionPane.showMessageDialog(this,
                        reservaBean.toString(),
                        "Reserva Confirmada",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (!cocinaOk) comboCocina.setBorder(BorderFactory.createLineBorder(Color.RED));
                if (!tipoOk) comboTipo.setBorder(BorderFactory.createLineBorder(Color.RED));

                String mensaje = ValidadorBK.obtenerMensajeError(txtNombre, txtTel, comboCocina, comboTipo);
                JOptionPane.showMessageDialog(this, mensaje, "Datos no válidos", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JScrollPane(main));
    }

    private ReservaBean rellenarBean() {
        ReservaBean reservaBean = new ReservaBean();
        reservaBean.setNombre(txtNombre.getText());
        reservaBean.setTelefono(txtTel.getText());
        String tipoSel = String.valueOf(comboTipo.getSelectedItem());
        reservaBean.setTipo(tipoSel);
        reservaBean.setPersonas((int) spinPers.getValue());
        reservaBean.setCocina(String.valueOf(comboCocina.getSelectedItem()));

        if ("Congreso".equals(tipoSel)) {
            reservaBean.setJornadas((int) spinJornadas.getValue());
            reservaBean.setHabitaciones(checkHab.isSelected());
        }
        return reservaBean;
    }

    // Método original mantenido pero simplificado con la clase EstiloReserva
    private JLabel crearSubtitulo(String texto) {
        return EstiloReserva.crearSubtitulo(texto);
    }

    public static void main(String[] args) {
        // Mejora visual para que Swing use el estilo del sistema operativo
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}