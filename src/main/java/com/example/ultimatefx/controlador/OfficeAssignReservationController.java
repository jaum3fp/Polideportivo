package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.modelos.OfficerAssignReservationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

/**
 * La clase OfficeAssignReservationController es el controlador de la vista de la asignación de reservas del oficinista
 * @author alumne
 * @version java 20
 */
public class OfficeAssignReservationController {

    @FXML
    private CheckBox confirmCheckBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button exitButton;
    @FXML
    private ListView<Reserva> reservationsListView;
    @FXML
    private Button assignationButton;
    @FXML
    private ComboBox<String> trainerDniCombo;

    @FXML
    private void initialize(){

        loadData();
        loadEvents();

    }

    private void loadData(){
        loadReservations();
        loadTrainers();
    }

    /**
     * Método que carga todas las reservas en la lista de reservas
     * @see OfficerAssignReservationModel#requestGetAllReservas()
     * @see #reservationsListView
     */
    private void loadReservations(){
        for (Reserva r: OfficerAssignReservationModel.getInstance().requestGetAllReservas()){
            reservationsListView.getItems().add(r);
        }
    }

    /**
     * Método que elimina todos los elementos de la lista de reservas
     * @see #reservationsListView
     */
    private void clearReservations(){
        reservationsListView.getItems().clear();
    }

    /**
     * Carga el dni de los entrenadores
     * @see OfficerAssignReservationModel#requestGetTrainers()
     * @see #trainerDniCombo
     */
    private void loadTrainers(){
        for (Persona p: OfficerAssignReservationModel.getInstance().requestGetTrainers()){
            trainerDniCombo.getItems().add(p.getDni());
        }

    }

    /**
     * Método que pide al modelo actualizar el estado de la reserva
     * @param status Especifica el estado que se quiere establecer
     * @see OfficerAssignReservationModel#requestSetReservationStatus(String, int, int)
     */
    private void setReservationStatus(int status){
        String entrenador = null;
        if (status == 2){
            entrenador = getUserDni();
        }
        int reserva = selectReservation().getId();
        OfficerAssignReservationModel.getInstance().requestSetReservationStatus(entrenador, reserva, status);
    }

    /**
     * @return Retorna la reserva seleccionada
     * @see #reservationsListView
     */
    private Reserva selectReservation(){
        return reservationsListView.getSelectionModel().getSelectedItem();
    }

    /**
     * @return Retorna si el usuario confirma que quiere cancelar
     * @see #confirmCheckBox
     */
    private boolean confirmationCancel(){
        return confirmCheckBox.isSelected();
    }

    /**
     * Carga los eventos de sus respectivos elementos
     * @see #assignationButton
     * @see #setReservationStatusButtonEvent()
     * @see #cancelButton
     * @see #setCancelationStatusButtonEvent()
     * @see #exitButton
     * @see #exitStage()
     */
    private void loadEvents(){
        assignationButton.setOnAction(event -> setReservationStatusButtonEvent());
        cancelButton.setOnAction(event -> setCancelationStatusButtonEvent());
        exitButton.setOnAction(event -> exitStage());
    }

    /**
     * Comprueba si el entrenador está capacitado y establece el entrenador
     * @see #trainerIsTrained()
     * @see #setReservationStatus(int 2)
     * @see #reloadReservations()
     */
    private void setReservationStatusButtonEvent(){
        if (trainerIsTrained()){
            setReservationStatus(2);
        }
        reloadReservations();
    }

    /**
     * Comprueba si se ha desbloqueado el boton de cancelar y establece la cancelación
     * @see #confirmationCancel()
     * @see #setReservationStatus(int 3)
     * @see #reloadReservations()
     */
    private void setCancelationStatusButtonEvent(){
        if (confirmationCancel()){
            setReservationStatus(3);
        }
        reloadReservations();
    }

    /**
     * Actualiza la lista de reservas
     * @see #clearReservations()
     * @see #loadReservations()
     */
    private void reloadReservations(){
        clearReservations();
        loadReservations();
    }

    /**
     * @return Retorna si el entrenador está capacitado para la actividad
     * @see OfficerAssignReservationModel#requestTrainerIsTrained(String, String)
     */
    private boolean trainerIsTrained(){
        String id = selectReservation().getNomActividad();
        String dni = getUserDni();
        return OfficerAssignReservationModel.getInstance().requestTrainerIsTrained(id, dni);
    }

    /**
     * @return Retorna el dni seleccionado en el desplegable
     * @see #trainerDniCombo
     */
    private String getUserDni(){
        return trainerDniCombo.getValue();
    }

    /**
     * Método encargado de cambiar la escena
     * @see Sistema#cambiarEscena(String, int, int)
     */
    private void exitStage(){
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }
}
