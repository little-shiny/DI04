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
        // Usamos StringBuilder para mayor eficiencia al concatenar
        StringBuilder sb = new StringBuilder();
        sb.append("DETALLES DE LA RESERVA\n");
        sb.append("=========================\n");
        sb.append("Cliente: ").append(nombre).append("\n");
        sb.append("Teléfono: ").append(telefono).append("\n");
        sb.append("Evento: ").append(tipo).append("\n");
        sb.append("Nº Asistentes: ").append(personas).append("\n"); // Salto de línea corregido aquí
        sb.append("Tipo de cocina: ").append(cocina); // Añadimos el nuevo campo con claridad

        // Mostramos extras solo si es Congreso (UD04 - Información relevante)
        if ("Congreso".equalsIgnoreCase(tipo)) {
            sb.append("\nJornadas: ").append(jornadas);
            sb.append("\nHabitaciones: ").append(habitaciones ? "Sí" : "No");
        }

        return sb.toString();
    }
}