package com.gui;

import com.componentes.BotonBK;
import javax.swing.*;
import java.awt.*;

public class VentanaReserva extends JFrame {

    // Componentes que necesitamos
    private JTextField txtNombre, txtTelefono;
    private JComboBox<String> comboTipo;
    private JSpinner spinJornadas;
    private JCheckBox checkHabitaciones;
    private JPanel panelCongreso;

    public VentanaReserva() {
        // Configuración básica (multiplataforma)
        setTitle("BK Gestión Hotelera - Salón Habana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null); // Centra la ventana

        // Panel Principal con margen para que no esté pegado al borde
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // --- SECCIÓN 1: DATOS (Agrupación por bordes con título ---
        JPanel pnlDatos = new JPanel(new GridLayout(0, 1, 5, 5));
        pnlDatos.setBackground(Color.WHITE);
        pnlDatos.setBorder(BorderFactory.createTitledBorder("Información de Contacto"));

        txtNombre = new JTextField();
        txtTelefono = new JTextField();

        pnlDatos.add(new JLabel("Nombre del Cliente:"));
        pnlDatos.add(txtNombre);
        pnlDatos.add(new JLabel("Teléfono:"));
        pnlDatos.add(txtTelefono);

        // --- SECCION 2: TIPO DE EVENTO ---
        JPanel pnlTipo = new JPanel(new GridLayout(0, 1, 5, 5));
        pnlTipo.setBackground(Color.WHITE);
        pnlTipo.setBorder(BorderFactory.createTitledBorder("Detalles del Evento"));

        String[] opciones = {"Banquete", "Jornada", "Congreso"};
        comboTipo = new JComboBox<>(opciones);

        pnlTipo.add(new JLabel("Seleccione el tipo:"));
        pnlTipo.add(comboTipo);

        // --- SECCIÓN 3: CONGRESO condicional) ---
        panelCongreso = new JPanel(new GridLayout(0, 1, 5, 5));
        panelCongreso.setBackground(new Color(245, 245, 245)); // Color gris suave para diferenciar
        panelCongreso.setBorder(BorderFactory.createTitledBorder("Extras para Congresos"));

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        checkHabitaciones = new JCheckBox("¿Necesita alojamiento?");

        //Deshabilitado por defecto
        spinJornadas.setEnabled(false);
        checkHabitaciones.setEnabled(false);

        panelCongreso.add(new JLabel("Número de jornadas:"));
        panelCongreso.add(spinJornadas);
        panelCongreso.add(checkHabitaciones);

        // --- LÓGICA DE EVENTOS--
        comboTipo.addActionListener(e -> {
            boolean esCongreso = comboTipo.getSelectedItem().equals("Congreso");

            //Si no es congreso, no molestamos al usuario
            spinJornadas.setEnabled(esCongreso);
            checkHabitaciones.setEnabled(esCongreso);

            // Cambio de color dinámico
            panelCongreso.setBackground(esCongreso ? new Color(230, 245, 255) : new Color(245, 245, 245));
        });

        // --- BOTÓN FINAL---
        BotonBK btnConfirmar = new BotonBK("CONFIRMAR RESERVA");

        // Añadimos todo al panel principal con espacios
        mainPanel.add(pnlDatos);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(pnlTipo);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(panelCongreso);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(btnConfirmar);

        add(new JScrollPane(mainPanel)); // Por si la pantalla es pequeña (Multiplataforma)
    }

    public static void main(String[] args) {
        // Para que se vea bien en todos los ordenadores
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}