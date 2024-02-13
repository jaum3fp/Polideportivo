package com.example.ultimatefx.dao;

/**
 * Esta clase representa un usuario del polideportivo.
 * @author alumne
 * @version java 20
 */
public class Persona {

    private String nombre;
    private String dni;
    private String password;
    private String type;
    private boolean banned;

    /**
     * Constructor de Persona con todos los parámetros menos banned
     * @param nombre nombre String
     * @param dni dni String
     * @param password contraseña String
     * @param type tipo int
     */
    public Persona(String nombre, String dni, String password, String type) {
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
        this.type = type;
    }

    /**
     * Constructor de Persona con todos los parámetros
     * @param nombre nombre String
     * @param dni dni String
     * @param password contraseña String
     * @param type tipo int
     * @param banned banned boolean
     */
    public Persona(String nombre, String dni, String password, String type, boolean banned) {
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
        this.type = type;
        this.banned = banned;
    }

    /**
     * Constructor de Persona con solo el dni
     * @param dni dni String
     */
    public Persona(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "NOMBRE: " + nombre + '\n' +
               "DNI: " + dni + '\n' +
               "CONTRASEÑA: " + password + '\n';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
