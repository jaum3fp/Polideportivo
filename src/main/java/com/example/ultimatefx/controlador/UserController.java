package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.fabrica.UserButtonFactory;
import com.example.ultimatefx.modelos.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * La clase UserController es el controlador de la vista de las funcionalidades de cada usuario
 * @author alumne
 * @version java 20
 */
public class UserController {

    @FXML
    public Label helloLabel;
    @FXML
    public ListView<Button> buttonsList;
    @FXML
    public TextArea resutlTextArea;
    private final UserButtonFactory factoryButton = UserButtonFactory.getInstance();

    /**
     * Al inicializar la lista se cargarán los botones correspondientes al usuario y se le saludará
     */
    @FXML
    private void initialize(){

        buttonsList.getItems().addAll(
            putUserButtons().getItems()
        );

        helloLabel.setText("Hola " + Sistema.getInstance().userSelected.getType() + " " + Sistema.getInstance().userSelected.getNombre() + "!");

    }

    /**
     * @return Retorna la lista de botones correspondientes al usuario logeado
     * @see Sistema#userSelected
     * @see #adminButtons()
     * @see #userButtons()
     * @see #officerButtons()
     * @see #trainerButtons()
     */
    private ListView<Button> putUserButtons(){
        ListView<Button> buttons = null;

        switch (Sistema.getInstance().userSelected.getType()){
            case "Administrador" -> buttons = adminButtons();
            case "Oficinista" -> buttons = officerButtons();
            case "Entrenador" -> buttons = trainerButtons();
            default -> buttons = userButtons();
        }
        return buttons;
    }

    /**
     * @return Retorna una lista de botones que tendrá el usuario cliente
     * @see UserButtonFactory#createButton(String)
     * @see #getGlobalButtons()
     */
    private ListView<Button> userButtons(){
        ListView<Button> buttons = new ListView<>();

        Button getOwnReservationsButton = factoryButton.createButton("VER RESERVAS");
        getOwnReservationsButton.setOnAction(onActionEvent -> showOwnReservations());

        buttons.getItems().addAll(
                getGlobalButtons().get(0),
                getGlobalButtons().get(2),
                getOwnReservationsButton,
                getGlobalButtons().get(1)
        );

        return buttons;
    }

    /**
     * @return Retorna una lista de botones que tendrá el administrador
     * @see UserButtonFactory#createButton(String)
     * @see #getGlobalButtons()
     */
    private ListView<Button> adminButtons(){
        ListView<Button> buttons = new ListView<>();

        buttons.getItems().addAll(
                getGlobalButtons().get(0),
                getGlobalButtons().get(1)
        );

        return buttons;
    }

    /**
     * @return Retorna una lista de botones que tendrá el oficinista
     * @see UserButtonFactory#createButton(String)
     * @see #getGlobalButtons()
     * @see Sistema#toggleToRegisterUser()
     * @see Sistema#toggleToDeleteUser()
     * @see Sistema#toggleToDisableUser()
     * @see Sistema#toggleToManageReservations()
     */
    private ListView<Button> officerButtons(){
        ListView<Button> buttons = new ListView<>();

        Button createUserButton = factoryButton.createButton("CREAR USUARIO");
        createUserButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToRegisterUser());

        Button deleteUserButton = factoryButton.createButton("ELIMINAR USUARIO");
        deleteUserButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToDeleteUser());

        Button sanctionUserButton = factoryButton.createButton("DESHABILITAR USUARIO");
        sanctionUserButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToDisableUser());

        Button manageReservationsButton = factoryButton.createButton("ADMINISTRAR RESERVAS");
        manageReservationsButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToManageReservations());

        buttons.getItems().addAll(
                getGlobalButtons().get(0),
                createUserButton,
                deleteUserButton,
                sanctionUserButton,
                getGlobalButtons().get(2),
                manageReservationsButton,
                getGlobalButtons().get(1)
        );

        return buttons;
    }

    /**
     * @return Retorna una lista de botones que tendrá el entrenador
     * @see UserButtonFactory#createButton(String)
     * @see #getGlobalButtons()
     * @see Sistema#toggleToSetCurriculum()
     */
    private ListView<Button> trainerButtons(){
        ListView<Button> buttons = new ListView<>();

        Button setCurriculumButton = factoryButton.createButton("CURRICULUM");
        setCurriculumButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToSetCurriculum());

        buttons.getItems().addAll(
                getGlobalButtons().get(0),
                setCurriculumButton,
                getGlobalButtons().get(1)
        );

        return buttons;
    }

    /**
     * @return Retorna una lista de botones globales
     * @see UserButtonFactory#createButton(String)
     * @see #showProfile()
     * @see Sistema#logOf()
     * @see Sistema#toggleToCalendarReservation()
     */
    private List<Button> getGlobalButtons(){
        List<Button> globalButtons = new ArrayList<>();

        Button getProfileButton = factoryButton.createButton("PERFIL");
        getProfileButton.setOnAction(onActionEvent -> showProfile());

        Button logOfButton = factoryButton.createButton("CERRAR SESIÓN");
        logOfButton.setOnAction(onActionEvent -> Sistema.getInstance().logOf());

        Button getReservationButton = factoryButton.createButton("HACER RESERVA");
        getReservationButton.setOnAction(onActionEvent -> Sistema.getInstance().toggleToCalendarReservation());

        globalButtons.add(getProfileButton);
        globalButtons.add(logOfButton);
        globalButtons.add(getReservationButton);

        return globalButtons;
    }

    /**
     * Método que muestra la información del usuario
     */
    private void showProfile(){
        resutlTextArea.setText(Sistema.getInstance().userSelected.toString());
    }

    /**
     * Método que muestra las reservas del usuario
     */
    private void showOwnReservations(){
        Set<Reserva> reservas = UserModel.getInstance().requestReservations();
        resutlTextArea.setText(reservas.toString());
    }

}
