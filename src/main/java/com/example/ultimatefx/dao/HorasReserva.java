package com.example.ultimatefx.dao;

import java.time.LocalTime;
/**
 * Esta clase representa la hora de una reserva del polideportivo
 * @author alumne
 * @version java 20
 */
public class HorasReserva {
    private LocalTime hora;
    private int id;

    /**
     * Constructor de la hora de la reserva con todos los parametros
     * @param id id de la hora
     * @param time hora de la hota
     */
    public HorasReserva(int id, LocalTime time){
        this.id=id;
        this.hora=time;
    }

    @Override
    public String toString() {
        return "HorasReserva{" +
                "time=" + hora +
                ", id=" + id +
                '}';
    }

    public LocalTime getTime() {
        return hora;
    }

    public void setTime(LocalTime time) {
        this.hora = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
