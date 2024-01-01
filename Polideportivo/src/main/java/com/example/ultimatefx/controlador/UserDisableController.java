package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.modelos.UserDisableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * La clase UserDisableController es el controlador de la vista de la deshabilitación de usuarios
 * @author alumne
 * @version java 20
 */
public class UserDisableController {

    @FXML
    private Button exitButton;
    @FXML
    private Button ableuserButton;
    @FXML
    private TextField txtDni;
    @FXML
    private CheckBox checkConfirm;
    @FXML
    private Button deluserButton;

    @FXML
    private void initialize(){
        loadEvents();
    }

    /**
     * Deshabilita usuario
     */
    private void enableUser(){
        String dni = getUserDni();
        UserDisableModel.getInstance().requestSetBanned(false, dni);
    }

    /**
     * Habilita usuario
     */
    private void unableUser(){
        String dni = getUserDni();
        UserDisableModel.getInstance().requestSetBanned(true, dni);
    }

    /**
     * @return Retorna el dni
     */
    private String getUserDni(){
        return txtDni.getText();
    }

    /**
     * @return Confirmar deshabilitación
     */
    private boolean confirmDisable(){
        return checkConfirm.isSelected();
    }

    private void loadEvents(){
        deluserButton.setOnAction(event -> unableUserButtonEvent());
        ableuserButton.setOnAction(event -> ableUserButtonEvent());
        exitButton.setOnAction(event -> exitStage());
    }

    /**
     * Método del evento encargado de deshabilitar un usuario
     * @see #confirmDisable()
     * @see #unableUser() ()
     * @see #exitStage()
     */
    private void unableUserButtonEvent(){
        if (confirmDisable()){
            unableUser();
            exitStage();
        }
    }

    /**
     * Método del evento encargado de habilitar un usuario
     * @see #confirmDisable()
     * @see #enableUser()
     * @see #exitStage()
     */
    private void ableUserButtonEvent(){
        if (confirmDisable()){
            enableUser();
            exitStage();
        }
    }

    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }
}
