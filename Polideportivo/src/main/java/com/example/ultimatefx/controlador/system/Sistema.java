package com.example.ultimatefx.controlador.system;

import com.example.ultimatefx.Main;
import com.example.ultimatefx.dao.Persona;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase sistema contiene información global entre todos los controladores
 * @author alumne
 * @version java 20
 */
public final class Sistema {
    /**
     * Atributo estático privado que contiene la instancia de la clase
     */
    private static Sistema instance;

    /**
     * Atributo que contiene el usuario que actualmente ha iniciado sesión
     */
    public Persona userSelected;
    /**
     * Atributo que contiene la escena actual
     */
    public Stage stage;

    /**
     * Constructor de Sistema privado
     */
    private Sistema(){}

    /**
     * Método que retorna la instancia de la clase.
     * En caso de que el atributo sea nulo, le asigna la instancia.
     * @return Instancia de la clase
     */
    public static Sistema getInstance(){
        if (instance==null) {
            instance = new Sistema();
        }
        return instance;
    }

    /**
     * Método encargado de cambial la vista
     * @param fxml Archivo fxml de la vista a cambiar String
     * @param v1 Asignar tamaño de la ventana int
     * @param v2 Asignar tamaño de la ventana int
     */
    public void cambiarEscena(String fxml, int v1, int v2) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Scene neo = new Scene(fxmlLoader.load(), v1, v2);
            stage.setScene(neo);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método cerrar sesión
     */
    public void logOf(){
        userSelected = null;
        cambiarEscena("login-view.fxml", 314, 393);
    }
    // CAMBIA DE VISTA
    public void toggleToCalendarReservation(){
        cambiarEscena("calendar-view.fxml", 900, 700);
    }
    // CAMBIA DE VISTA
    public void toggleToRegisterUser(){
        cambiarEscena("user-register-view.fxml", 360, 500);
    }
    // CAMBIA DE VISTA
    public void toggleToDeleteUser(){
        cambiarEscena("user-delete-view.fxml", 314, 393);
    }
    // CAMBIA DE VISTA
    public void toggleToSetCurriculum(){
        cambiarEscena("trainer-curriculum-view.fxml", 900, 700);
    }
    // CAMBIA DE VISTA
    public void toggleToDisableUser(){
        cambiarEscena("user-disable-view.fxml", 900, 700);
    }
    // CAMBIA DE VISTA
    public void toggleToManageReservations(){
        cambiarEscena("officer-assign-reservation-view.fxml", 900, 700);
    }

}
