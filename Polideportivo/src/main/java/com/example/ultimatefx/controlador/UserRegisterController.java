package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.modelos.UserRegisterModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Set;

/**
 * La clase CalendarController es el controlador de la vista de la registración de usuarios
 * @author alumne
 * @version java 20
 */
public class UserRegisterController {

    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label txtError;
    @FXML
    private TextField txtPasswd;
    @FXML
    private TextField txtRePasswd;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDni;
    @FXML
    private ToggleGroup userType;

    @FXML
    private void initialize(){
        loadEvents();
    }

    /**
     * Registrar usuario
     * @see #testData()
     * @see #createUser(String, String, String)
     * @see #isWorker()
     * @see #createWorker(String, int)
     * @see #getTxtError()
     */
    private void userRegister(){
        String name = getUserName();
        String dni = getUserDni();
        String passwd = getUserPasswd();
        if (testData()){
            createUser(name, dni, passwd);
            if (isWorker()){
                String typeUser = getUserType();
                createWorker(dni, getTypeNumber(typeUser));
            }
        } else {
            getTxtError();
        }

    }

    /**
     * Crea un usuario
     * @param nom Nombre String
     * @param dni Dni String
     * @param passwd Contraseña String
     */
    private void createUser(String nom, String dni, String passwd){
        UserRegisterModel.getInstance().requestSetUser(nom, dni, passwd);
    }

    /**
     * Convierte el tipo de trabajador a numero segun la base de datos
     * @param nomType Nombre tipo String
     * @return El numero correspondiente
     */
    private int getTypeNumber(String nomType){
        int numType;
        switch (nomType){
            case "Administrador" -> numType = 1;
            case "Oficinista" -> numType = 2;
            case "Entrenador" -> numType = 3;
            default -> numType = 0;
        }
        return numType;
    }

    /**
     * Crea un trabajador
     * @param dni Dni trabajador String
     * @param type Tipo trabajador int
     */
    private void createWorker(String dni, int type){
        UserRegisterModel.getInstance().requestSetEmployee(dni, type);
    }

    /**
     * @return Comprueba si el usuario es trabajador
     */
    private boolean isWorker(){
        String typeUser = getUserType();
        return !typeUser.equals("Cliente");
    }

    /**
     * @return Comprueba que ningún campo esté vacio
     */
    private boolean areNotEmpty(){
        boolean userNameDone = getUserName().isEmpty();
        boolean userDniDone = getUserDni().isEmpty();
        boolean passwdDone = getUserPasswd().isEmpty();
        boolean rePasswdDone = getUserRePasswd().isEmpty();
        boolean userTypeDone = userTypeNotSelected();
        return !userNameDone && !userDniDone && !passwdDone && !rePasswdDone && userTypeDone;
    }

    /**
     * @return Comprueba que el dni no esté repetido
     */
    private boolean validateUserDni(){
        String userDni = getUserDni();
        Set<String> userDniList = UserRegisterModel.getInstance().requestUserDnis();
        return !userDniList.contains(userDni);
    }

    /**
     * @return Comprueba que la contraseña no esté repetida
     */
    private boolean validatePassword(){
        String passwd = getUserPasswd();
        String rePasswd = getUserRePasswd();
        return passwd.equals(rePasswd);
    }

    /**
     * @return Comprueba el estado de los datos introducidos por el usuario
     */
    private boolean testData(){
        return areNotEmpty() && validateUserDni() && validatePassword();
    }

    // Recibe un nombre
    private String getUserName(){
        return txtNombre.getText();
    }

    // Recibe un dni
    private String getUserDni(){
        return txtDni.getText();
    }

    // Recibe una contraseña
    private String getUserPasswd(){
        return txtPasswd.getText();
    }

    // Recibe otra contraseña
    private String getUserRePasswd(){
        return txtRePasswd.getText();
    }

    // Recibe el tipo de usuario
    public String getUserType(){
        return ((RadioButton)userType.getSelectedToggle()).getText();
    }

    /**
     * Selecciona el mensaje en base al tipo de error
     */
    private void getTxtError(){
        if (!areNotEmpty()){
            setTextError("No pueden haber campos vacíos!");
        }
        if (!validateUserDni()){
            setTextError("Este dni ya existe!");
        }
        if (!validatePassword()){
            setTextError("Las contraseñas no coinciden!");
        }
    }

    // Carga el texto del error
    private void setTextError(String txt){
        txtError.setText(txt);
    }

    /**
     * @return Comprueba que el selector esté seleccionado
     */
    private boolean userTypeNotSelected(){
        return !((userType.getSelectedToggle()) == null);
    }

    private void loadEvents(){
        registerButton.setOnAction(event -> userRegister());
        exitButton.setOnAction(event -> exitStage());
    }

    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }
}
