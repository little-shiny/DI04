package com.gui;

import com.componentes.BotonBK;
import com.modelo.ReservaBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class VentanaReserva extends JFrame {
    private JTextField txtNombre, txtTel;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboCocina;
    private JSpinner spinPers, spinJornadas;
    private JCheckBox checkHab;
    private JPanel panelExtra;

    public VentanaReserva() {
        setTitle("BK Salón Habana - Formulario de Reserva");
        setSize(500, 820);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(EstiloReserva.FONDO_CREMA);

        //Creamos un borde sutil para el contenedor principal
        main.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(EstiloReserva.BORDE_MATIZ, 2),
                new EmptyBorder(20, 25, 20, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // --- Logo ---
        gbc.gridy = 0; gbc.gridx = 0; gbc.gridwidth = 2;
        JLabel lblLogo = new JLabel();
        try {
            URL urlLogo = getClass().getResource("/LogoBK.png");
            if (urlLogo != null) {
                ImageIcon iconoOriginal = new ImageIcon(urlLogo);
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imgEscalada));
            }
        } catch (Exception e) { /* Fallback */ }
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setBorder(new EmptyBorder(0, 0, 15, 0));
        main.add(lblLogo, gbc);

        // --- SECCIÓN 1: CONTACTO ---
        gbc.gridy = 1;
        main.add(EstiloReserva.crearSubtitulo("1. DATOS DE CONTACTO"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2; gbc.gridx = 0;
        main.add(crearLabelEstilizada("Nombre Completo:"), gbc); // USO DE FUENTE_LABEL

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        main.add(txtNombre, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        main.add(crearLabelEstilizada("Teléfono:"), gbc);

        gbc.gridx = 1;
        txtTel = new JTextField(15);
        main.add(txtTel, gbc);

        // --- SECCIÓN 2: DETALLES ---
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        main.add(EstiloReserva.crearSubtitulo("2. DETALLES DEL EVENTO"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 5; gbc.gridx = 0;
        main.add(crearLabelEstilizada("Tipo de Evento:"), gbc);

        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Banquete", "Jornada", "Congreso"});
        comboTipo.setSelectedIndex(-1);
        main.add(comboTipo, gbc);

        gbc.gridy = 6; gbc.gridx = 0;
        main.add(crearLabelEstilizada("Nº Asistentes:"), gbc);

        gbc.gridx = 1;
        spinPers = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        main.add(spinPers, gbc);

        gbc.gridy = 7; gbc.gridx = 0;
        main.add(crearLabelEstilizada("Tipo de cocina:"), gbc);
        gbc.gridx = 1;
        comboCocina = new JComboBox<>(new String[]{"Bufé", "Carta", "Chef", "No precisa"});
        comboCocina.setSelectedIndex(-1);
        main.add(comboCocina, gbc);

        // --- SECCIÓN 3: EXTRAS ---
        gbc.gridy = 8; gbc.gridx = 0; gbc.gridwidth = 2;
        panelExtra = new JPanel(new GridBagLayout());
        panelExtra.setBackground(Color.WHITE);

        // Matiz en el borde del panel extra
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(EstiloReserva.BORDE_MATIZ), "Opciones Congreso");
        border.setTitleColor(EstiloReserva.AZUL_MARINO);
        panelExtra.setBorder(border);

        GridBagConstraints gbcExtra = new GridBagConstraints();
        gbcExtra.insets = new Insets(5, 5, 5, 5);

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinJornadas.setEnabled(false);
        checkHab = new JCheckBox("Habitaciones");
        checkHab.setFont(EstiloReserva.FUENTE_LABEL);
        checkHab.setBackground(Color.WHITE);
        checkHab.setEnabled(false);

        gbcExtra.gridx = 0; gbcExtra.gridy = 0;
        panelExtra.add(crearLabelEstilizada("Jornadas:"), gbcExtra);
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
        });

        // --- BOTÓN ---
        gbc.gridy = 9; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 5, 5, 5);

        BotonBK btn = new BotonBK("CONFIRMAR");
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setBackground(EstiloReserva.NARANJA_BK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 45));

        main.add(btn, gbc);

        btn.addActionListener(e -> {
            if (ValidadorBK.validarNombre(txtNombre) && ValidadorBK.validarTelefono(txtTel) &&
                    ValidadorBK.validarCombo(comboCocina) && ValidadorBK.validarCombo(comboTipo)) {
                ReservaBean rb = rellenarBean();
                JOptionPane.showMessageDialog(this, rb.toString());
            }
        });

        add(new JScrollPane(main));
    }

    /**
     * Método auxiliar para aplicar FUENTE_LABEL de EstiloReserva a todas las etiquetas
     */
    private JLabel crearLabelEstilizada(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(EstiloReserva.FUENTE_LABEL); // USO DE FUENTE_LABEL
        return label;
    }

    private ReservaBean rellenarBean() {
        ReservaBean rb = new ReservaBean();
        rb.setNombre(txtNombre.getText());
        rb.setTelefono(txtTel.getText());
        rb.setTipo(String.valueOf(comboTipo.getSelectedItem()));
        rb.setPersonas((int) spinPers.getValue());
        rb.setCocina(String.valueOf(comboCocina.getSelectedItem()));
        if ("Congreso".equals(rb.getTipo())) {
            rb.setJornadas((int) spinJornadas.getValue());
            rb.setHabitaciones(checkHab.isSelected());
        }
        return rb;
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}