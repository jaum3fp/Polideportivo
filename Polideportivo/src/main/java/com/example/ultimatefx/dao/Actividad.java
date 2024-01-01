package com.example.ultimatefx.dao;

/**
 * Esta clase representa la actividad del polideportivo.
 * @author alumne
 * @version java 20
 */
public class Actividad {
    private int id;
    private String nombre;
    private int limite;

    /**
     * Constructor de Actividad con todos los atributos
     * @param id Id de la actividad int
     * @param nombre Nombre de la actividad String
     * @param limite Límite de usuarios de la actividad int
     */
    public Actividad(int id, String nombre, int limite) {
        this.id = id;
        this.nombre = nombre;
        this.limite = limite;
    }

    /**
     * Constructor de Actividad sin el atributo límite
     * @param id Id de la actividad int
     * @param nombre Nombre de la actividad String
     */
    public Actividad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor de Actividad sin parámetros
     */
    public Actividad(){}

    @Override
    public String toString() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
