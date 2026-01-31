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
        setTitle("BK Salón Habana - Formulario de Reserva");
        setSize(480, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con scroll
        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(Color.WHITE);
        main.setBorder(new EmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración común para los componentes
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que rellenen su hueco
        gbc.anchor = GridBagConstraints.WEST; // Alineados a la izquierda

        // --- SECCIÓN 1: CONTACTO ---
        gbc.gridwidth = 2; // Ocupa dos columnas
        main.add(crearSubtitulo("1. DATOS DE CONTACTO"), gbc);

        gbc.gridwidth = 1; // Volvemos a una columna
        gbc.gridy = 1; gbc.gridx = 0;
        main.add(new JLabel("Nombre Completo:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        main.add(txtNombre, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        main.add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        txtTel = new JTextField(15);
        main.add(txtTel, gbc);

        // --- SECCIÓN 2: DETALLES ---
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        main.add(crearSubtitulo("2. DETALLES DEL EVENTO"), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 4; gbc.gridx = 0;
        main.add(new JLabel("Tipo de Evento:"), gbc);

        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Banquete", "Jornada", "Congreso"});
        main.add(comboTipo, gbc);

        gbc.gridy = 5; gbc.gridx = 0;
        main.add(new JLabel("Nº Asistentes:"), gbc);

        gbc.gridx = 1;
        // Ajustamos el Spinner para que no sea pequeño
        spinPers = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
        spinPers.setPreferredSize(new Dimension(80, 25));
        main.add(spinPers, gbc);

        // NUEVO CAMPO: TIPO DE COCINA (Basado en UD04 - Consistencia)
        gbc.gridy = 6; gbc.gridx = 0;
        main.add(new JLabel("Tipo de cocina:"), gbc);

        gbc.gridx = 1;
        String[] opcionesCocina = {"Bufé", "Carta", "Pedir cita con el chef", "No precisa"};
        JComboBox<String> comboCocina = new JComboBox<>(opcionesCocina);
        main.add(comboCocina, gbc);


        // --- SECCIÓN 3 extras
        gbc.gridy = 6; gbc.gridx = 0; gbc.gridwidth = 2;
        panelExtra = new JPanel(new GridBagLayout());
        panelExtra.setBackground(new Color(245, 245, 245));
        panelExtra.setBorder(BorderFactory.createTitledBorder("Opciones Congreso"));

        GridBagConstraints gbcExtra = new GridBagConstraints();
        gbcExtra.insets = new Insets(5, 5, 5, 5);
        gbcExtra.anchor = GridBagConstraints.WEST;

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinJornadas.setEnabled(false);
        checkHab = new JCheckBox("Habitaciones");
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
            boolean esCongreso = comboTipo.getSelectedItem().equals("Congreso");
            spinJornadas.setEnabled(esCongreso);
            checkHab.setEnabled(esCongreso);
            panelExtra.setBackground(esCongreso ? new Color(230, 240, 255) : new Color(245, 245, 245));
        });

        // --- BOTÓN ---
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; // El botón no se estira
        gbc.anchor = GridBagConstraints.CENTER;
        BotonBK btn = new BotonBK("CONFIRMAR");
        main.add(btn, gbc);

        btn.addActionListener(e -> {
            // Verificamos ambos campos
            boolean nombreValido = ValidadorBK.validarNombre(txtNombre);
            boolean telValido = ValidadorBK.validarTelefono(txtTel);

            if (nombreValido && telValido) {
                // creamos bean
                ReservaBean reservaBean = new ReservaBean();

                // Rellenamos los datos
                reservaBean.setNombre(txtNombre.getText());
                reservaBean.setTelefono(txtTel.getText());
                String tipoSeleccionado = comboTipo.getSelectedItem().toString();
                reservaBean.setTipo(tipoSeleccionado);
                reservaBean.setPersonas((int) spinPers.getValue());
                reservaBean.setCocina(comboCocina.getSelectedItem().toString());

                // Se rellenan los datos si es un congreso
                if("Congreso".equals(tipoSeleccionado)){
                    reservaBean.setJornadas((int) spinJornadas.getValue());
                    reservaBean.setHabitaciones(checkHab.isSelected());
                }

                // Mostramos el toString del bean
                JOptionPane.showMessageDialog(this,
                        reservaBean.toString(),
                        "Reserva Confirmada",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostramos el mensaje dinámico que explica el error
                String mensaje = ValidadorBK.obtenerMensajeError(txtNombre, txtTel);
                JOptionPane.showMessageDialog(this, mensaje, "Datos no válidos", JOptionPane.WARNING_MESSAGE);
            }
        });

        add(new JScrollPane(main));
    }
    private JLabel crearSubtitulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        l.setForeground(new Color(0, 51, 153));
        l.setBorder(new EmptyBorder(15, 0, 5, 0));
        return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}