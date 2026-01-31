package com.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservaBean implements Serializable {
    private String nombre = "", telefono = "", tipo = "", cocina = "";
    private int personas, jornadas;
    private boolean habitaciones;
    private Date fecha = new Date();

    public ReservaBean() {
    }

    // Getters y Setters

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public void setTelefono(String t) {
        this.telefono = t;
    }

    public void setTipo(String t) {
        this.tipo = t;
    }

    public void setCocina(String c) {
        this.cocina = c;
    }

    public void setPersonas(int p) {
        this.personas = p;
    }

    public void setJornadas(int j) {
        this.jornadas = j;
    }

    public void setHabitaciones(boolean h) {
        this.habitaciones = h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("📋 RESUMEN DE RESERVA\n");
        sb.append("------------------------------------\n");

        sb.append("👤 Cliente: ").append(nombre).append("\n");
        sb.append("📞 Teléfono: ").append(telefono).append("\n");
        sb.append("\uD83D\uDCC6  Fecha: ").append(sdf.format(fecha)).append("\n");
        sb.append("🗓 Evento: ").append(tipo).append("\n");
        sb.append("👥 Asistentes: ").append(personas).append("\n");
        sb.append("🍳 Cocina: ").append(cocina).append("\n"); // Salto de línea añadido

        if ("Congreso".equals(tipo)) {
            sb.append("⏳ Jornadas: ").append(jornadas).append("\n");
            sb.append("🏨 Habitaciones: ").append(habitaciones ? "Solicitadas" : "No requiere");
        }
        return sb.toString();
    }

}