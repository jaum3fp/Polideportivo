package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.modelos.LoginModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * La clase LoginController es el controlador de la vista del login del usuario
 * @author alumne
 * @version java 20
 */
public class LoginController{

    @FXML
    private Label txtResult;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;

    @FXML
    private void initialize(){
        loadEvents();
    }

    /**
     * Inicia sesión o retorna un error
     * @param user Usuario a logear
     * @see #testData(Persona)
     * @see Sistema#userSelected
     * @see #exitStage()
     * @see #serTextError(Persona)
     */
    private void login(Persona user){
        if (testData(user)){
            Sistema.getInstance().userSelected = user;
            exitStage();
        } else {
            serTextError(user);
        }
    }

    /**
     * Evento del botón que envia los datos y logea el usuario, o que muestra un error
     * @see LoginModel#userdataRequest(String, String)
     * @see #login(Persona)
     */
    private void btnSubmitEvent(){
        String dni = txtUser.getText();
        String passwd = txtPassword.getText();
        Persona user = LoginModel.getInstance().userdataRequest(dni, passwd);
        login(user);
    }

    /**
     * @param user Usuario a testear
     * @return retorna false en caso de que el usuario sea nulo o esté deshabilitado
     */
    private boolean testData(Persona user){
        if (user!=null)
            return !getIsBanned(user);
        else
            return false;
    }

    /**
     * @param user Muestra el texto del aviso correspondiente
     * @see #txtResult
     */
    private void serTextError(Persona user){
        if (user==null){
            txtResult.setText("Usuario o contraseña incorrectos");
        } else if (getIsBanned(user)) {
            txtResult.setText("Usuario deshabilitado");
        }
    }

    /**
     * @param user Usuario a comprobar
     * @return Retorna si el usuario está deshabilitado o no
     */
    private boolean getIsBanned(Persona user){
        return user.isBanned();
    }

    /**
     * Carga los eventos en sus respectivos elementos
     * @see #btnSubmit
     * @see #btnSubmitEvent()
     */
    private void loadEvents() {
        btnSubmit.setOnAction(event -> btnSubmitEvent());
    }

    /**
     * Método encargado de cambiar la escena
     * @see Sistema#cambiarEscena(String, int, int)
     */
    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 700);
    }

}