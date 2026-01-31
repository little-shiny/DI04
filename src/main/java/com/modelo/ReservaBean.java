package com.modelo;

import java.io.Serializable;

/**
 * Esta clase guarda los datos de la reserva.
 * Es un JavaBean porque: es serializable, tiene constructor vacío y getters/setters.
 * Esto permite que los datos "sobrevivan" y puedan ser procesados luego.
 */
public class ReservaBean implements Serializable {
    public String nombre, telefono, tipo, cocina;
    public int personas, jornadas;
    public boolean habitaciones;

    public ReservaBean() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCocina(String cocina) {
        this.cocina = cocina;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public void setJornadas(int jornadas) {
        this.jornadas = jornadas;
    }

    public void setHabitaciones(boolean habitaciones) {
        this.habitaciones = habitaciones;
    }

    // Método para resumir la reserva al final
    @Override
    public String toString() {
        return "RESUMEN DE RESERVA:\n" +
                "--------------------------\n" +
                "Responsable: " + nombre + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Evento: " + tipo + "\n" +
                "Asistentes: " + personas + "\n" +
                "Cocina: " + cocina + "\n" +
                (tipo.equals("Congreso") ? "Jornadas: " + jornadas + "\nHabitaciones: " + (habitaciones ? "Sí" : "No") : "");
    }
}