package com.modelo;

import java.io.Serializable;

/**
 * Esta clase guarda los datos de la reserva.
 * Es un JavaBean porque: es serializable, tiene constructor vacío y getters/setters.
 * Esto permite que los datos "sobrevivan" y puedan ser procesados luego.
 */
public class ReservaBean implements Serializable {
    private String cliente;
    private String tipoEvento; // Banquete, Jornada o Congreso

    public ReservaBean() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipo) {
        this.tipoEvento = tipo;
    }
}