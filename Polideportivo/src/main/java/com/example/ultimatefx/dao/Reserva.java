package com.example.ultimatefx.dao;

import java.time.LocalDateTime;

/**
 * Esta clase representa una reserva del polideportivo.
 * @author alumne
 * @version java 20
 */
public class Reserva {
    private int id;
    private String dniCliente;
    private LocalDateTime fecha;
    private String nomActividad;
    private String nomEstadoReserva;
    private String dniEmpleado;

    /**
     * Constructor con todos los parametros
     * @param id id int
     * @param dniCliente dni String
     * @param fecha fecha LocalDateTime
     * @param nomActividad nom String
     * @param nomEstadoReserva nom Strng
     * @param dniEmpleado dni String
     */
    public Reserva(int id, String dniCliente, LocalDateTime fecha, String nomActividad, String nomEstadoReserva, String dniEmpleado) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.fecha = fecha;
        this.nomActividad = nomActividad;
        this.nomEstadoReserva = nomEstadoReserva;
        this.dniEmpleado = dniEmpleado;
    }

    /**
     *  Constructor con casi todos los parametros
     * @param id id int
     * @param dniCliente dni String
     * @param fecha fecha LocalDateTime
     * @param nomActividad nom String
     * @param dniEmpleado dni String
     */
    public Reserva(int id, String dniCliente, LocalDateTime fecha, String nomActividad, String dniEmpleado) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.fecha = fecha;
        this.nomActividad = nomActividad;
        this.dniEmpleado = dniEmpleado;
    }

    /**
     *  Constructor con casi todos los parametros
     * @param id id int
     * @param dniCliente dni String
     * @param fecha fecha LocalDateTime
     * @param nomActividad nom String
     */
    public Reserva(int id, String dniCliente, LocalDateTime fecha, String nomActividad) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.fecha = fecha;
        this.nomActividad = nomActividad;
    }

    @Override
    public String toString() {
        return "[ID: " + id +
               " Actividad: " + nomActividad +
               " Fecha: " + fecha.toString().substring(0, 10) +
               " Hora: " + fecha.toString().substring(11) +
               " Estado: " + nomEstadoReserva +
               " Entrenador: " + dniEmpleado +
               "]\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    public String getNomEstadoReserva() {
        return nomEstadoReserva;
    }

    public void setNomEstadoReserva(String nomEstadoReserva) {
        this.nomEstadoReserva = nomEstadoReserva;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }
}
