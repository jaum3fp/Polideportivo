package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.modelos.UserDeleteModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * La clase UserDeleteController es el controlador de la vista de la borración de usuarios
 * @author alumne
 * @version java 20
 */
public class UserDeleteController {

    @FXML
    private Button exitButton;
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
     * @return Retorna si se ha habilitado el boton de eliminar usuario
     * @see #checkConfirm
     */
    private boolean confirmDelete(){
        return checkConfirm.isSelected();
    }

    /**
     * Método encargado de eliminar un usuario
     * @see #confirmDelete()
     * @see UserDeleteModel#requestDeleteUser(String)
     */
    private void deleteUser(){
        String dni = txtDni.getText();
        if (confirmDelete()){
            UserDeleteModel.getInstance().requestDeleteUser(dni);
        }
    }

    private void loadEvents(){
        deluserButton.setOnAction(event -> deleteUser());
        exitButton.setOnAction(event -> exitStage());
    }
    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }
}
