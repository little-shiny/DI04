package com.modelo;

import java.io.Serializable;

/**
 * Esta clase guarda los datos de la reserva.
 * Es un JavaBean porque: es serializable, tiene constructor vacío y getters/setters.
 * Esto permite que los datos "sobrevivan" y puedan ser procesados luego.
 */
public class ReservaBean implements Serializable {
    public String nombre;
    public String telefono;
    public String tipo;
    public String cocina;
    public int personas, jornadas;
    public boolean habitaciones;

    public ReservaBean() {
    }

    public boolean isHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(boolean habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getJornadas() {
        return jornadas;
    }

    public void setJornadas(int jornadas) {
        this.jornadas = jornadas;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getCocina() {
        return cocina;
    }

    public void setCocina(String cocina) {
        this.cocina = cocina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para resumir la reserva al final
    @Override
    public String toString() {
        // Construimos el mensaje base
        String mensaje = "DETALLES DE LA RESERVA\n" +
                "======================\n" +
                "Cliente: " + nombre + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Evento: " + tipo + "\n" +
                "Nº Asistentes: " + personas;

        // Añadimos extras solo si es Congreso
        if ("Congreso".equals(tipo)) {
            mensaje += "\nNº Jornadas: " + jornadas +
                    "\nAlojamiento: " + (habitaciones ? "Solicitado" : "No requerido");
        }

        return mensaje;
    }
}