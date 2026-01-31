package com.gui;

import com.componentes.BotonBK;
import com.modelo.ReservaBean;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaReserva extends JFrame {

    // Componentes del formulario
    private JTextField txtNombre, txtTel;
    private JComboBox<String> comboTipo, comboCocina;
    private JSpinner spinPers, spinJornadas;
    private JCheckBox checkHab;
    private JPanel panelExtra;

    // Colores para los subtítulos (UD04 - Jerarquía visual)
    private final Color azulSubtitulo = new Color(0, 51, 153);

    public VentanaReserva() {
        setTitle("BK Salón Habana - Registro de Reservas");
        setSize(480, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con scroll para que sea usable en cualquier pantalla (Multiplataforma)
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        main.setBackground(Color.WHITE);

        // --- SECCIÓN 1: CONTACTO ---
        main.add(crearSubtitulo("1. INFORMACIÓN DEL RESPONSABLE"));
        txtNombre = new JTextField();
        txtTel = new JTextField();

        main.add(new JLabel("Nombre Completo:"));
        main.add(txtNombre);
        main.add(new JLabel("Teléfono de contacto:"));
        main.add(txtTel);

        main.add(Box.createVerticalStrut(20));

        // --- SECCIÓN 2: DETALLES ---
        main.add(crearSubtitulo("2. DETALLES DEL EVENTO"));

        String[] tipos = {"Banquete", "Jornada", "Congreso"};
        comboTipo = new JComboBox<>(tipos);

        String[] cocinas = {"Bufé", "Carta", "Cita con Chef", "No precisa"};
        comboCocina = new JComboBox<>(cocinas);

        spinPers = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));

        main.add(new JLabel("Tipo de Evento:"));
        main.add(comboTipo);
        main.add(new JLabel("Número de asistentes:"));
        main.add(spinPers);
        main.add(new JLabel("Preferencia de Cocina:"));
        main.add(comboCocina);

        main.add(Box.createVerticalStrut(20));

        // --- SECCIÓN 3: EXTRAS (Panel dinámico) ---
        main.add(crearSubtitulo("3. OPCIONES DE CONGRESO"));
        panelExtra = new JPanel(new GridLayout(0, 1, 5, 5));
        panelExtra.setBackground(new Color(245, 245, 245));

        spinJornadas = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
        checkHab = new JCheckBox("¿Necesitan habitaciones los asistentes?");

        // Empezamos con esto deshabilitado según los requisitos de la Tarea 1
        spinJornadas.setEnabled(false);
        checkHab.setEnabled(false);

        panelExtra.add(new JLabel("Días del congreso:"));
        panelExtra.add(spinJornadas);
        panelExtra.add(checkHab);
        main.add(panelExtra);

        // LÓGICA DE ACTIVACIÓN
        comboTipo.addActionListener(e -> {
            boolean esCongreso = comboTipo.getSelectedItem().equals("Congreso");
            spinJornadas.setEnabled(esCongreso);
            checkHab.setEnabled(esCongreso);
            // Feedback visual: cambiamos el fondo si está activo
            panelExtra.setBackground(esCongreso ? new Color(230, 245, 255) : new Color(245, 245, 245));
        });

        // --- BOTÓN DE ENVÍO ---
        main.add(Box.createVerticalStrut(25));
        BotonBK btnConfirmar = new BotonBK("CONFIRMAR Y MOSTRAR DATOS");
        main.add(btnConfirmar);

        // Acción al pulsar el botón
        btnConfirmar.addActionListener(e -> procesarFormulario());

        add(new JScrollPane(main));
    }

    /**
     * Método para crear los títulos de sección con color.
     */
    private JLabel crearSubtitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(azulSubtitulo); // Aplicamos el color que pediste
        label.setBorder(new EmptyBorder(5, 0, 5, 0));
        return label;
    }

    /**
     * Recoge los datos, valida y muestra el resumen.
     */
    private void procesarFormulario() {
        // Validamos usando la clase externa
        boolean errorNombre = ValidadorBK.campoVacio(txtNombre);
        boolean errorTel = ValidadorBK.telefonoInvalido(txtTel);

        if (errorNombre || errorTel) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, revisa los campos en rojo.",
                    "Datos incorrectos",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            // Si todo está bien, llenamos el Bean
            ReservaBean datos = new ReservaBean();
            datos.nombre = txtNombre.getText();
            datos.telefono = txtTel.getText();
            datos.tipo = comboTipo.getSelectedItem().toString();
            datos.cocina = comboCocina.getSelectedItem().toString();
            datos.personas = (int) spinPers.getValue();
            datos.jornadas = (int) spinJornadas.getValue();
            datos.habitaciones = checkHab.isSelected();

            // Mostramos el resumen final (UX: Confirmación del trabajo realizado)
            JOptionPane.showMessageDialog(this,
                    datos.toString(),
                    "Reserva Registrada con Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReserva().setVisible(true));
    }
}