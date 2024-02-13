package com.example.ultimatefx.controlador;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.modelos.TrainerCurriculumModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * La clase TrainerCurriculumController es el controlador de la vista de la asignación de reservas del oficinista
 * @author alumne
 * @version java 20
 */
public class TrainerCurriculumController {

    @FXML
    private Button exitButton;
    @FXML
    private ListView<Actividad> activitysCurriculum;
    @FXML
    private ListView<Actividad> activitysUntrained;
    @FXML
    private Button cantButton;
    @FXML
    private Button canButton;

    private Actividad trainerActivitySelected = null;

    @FXML
    private void initialize(){
        loadData();
        loadEvents();
    }

    /**
     * Método que corga las actividades en sus respectivas listas
     * @see #activitysCurriculum
     * @see #getCurriculum()
     * @see #activitysCurriculum
     * @see #getActivitys()
     * @see #activitysUntrained
     * @see #getActivitysNotInCurriculum()
     */
    private void loadData(){
        activitysCurriculum.getItems().addAll(getCurriculum());
        if (activitysCurriculum.getItems().isEmpty()){
            activitysUntrained.getItems().addAll(getActivitys());
        } else {
            activitysUntrained.getItems().addAll(getActivitysNotInCurriculum());
        }

    }
    /**
     * Método que selecciona la actividad de la lista de actividades que no se pueden hacer
     * @see #trainerActivitySelected
     * @see #activitysUntrained
     */
    private void selectActivityUntrained(){
        trainerActivitySelected = activitysUntrained.getSelectionModel().getSelectedItem();
    }

    /**
     * Método que selecciona la actividad de la lista de actividades que se pueden hacer
     * @see #trainerActivitySelected
     * @see #activitysCurriculum
     */
    private void selectActivityCurriculum(){
        trainerActivitySelected = activitysCurriculum.getSelectionModel().getSelectedItem();
    }

    /**
     * @param activitys lista de actividades a combrobar
     * @return Retorna si la actividad está en la lista
     */
    private boolean activityIsIn(List<Actividad> activitys){
        boolean notIn = false;
        for (Actividad s: activitys){
            if (trainerActivitySelected.getNombre().equals(s.getNombre())) {
                notIn = true;
                break;
            }
        }
        return notIn;
    }

    /**
     * Método que comprueba que la actividad puede ser movida de la lista
     * @param activitys Actividad seleccionada por el usuario
     * @return Retorna si puede o no
     */
    private boolean canBeMoved(List<Actividad> activitys){
        boolean activityIsNotNull = trainerActivitySelected != null;
        return activityIsNotNull && activityIsIn(activitys);
    }

    /**
     * Método encargado de mover la actividad seleccionada de lista
     * @param inputList Lista a añadir acividad
     * @param outputList Lista a quitar actividad
     * @see #trainerActivitySelected
     */
    private void moveActivity(ListView<Actividad> inputList, ListView<Actividad> outputList){
        outputList.getItems().remove(trainerActivitySelected);
        inputList.getItems().add(trainerActivitySelected);
        trainerActivitySelected = null;
    }

    /**
     * @return Retorna una lista con las actividades del polideportivo
     * @see TrainerCurriculumModel#requestGetActividades()
     */
    private List<Actividad> getActivitys(){
        return TrainerCurriculumModel.getInstance().requestGetActividades();
    }

    /**
     * @return Retorna una lista con las actividades que el entrenador puede hacer
     * @see #getUserDni()
     * @see TrainerCurriculumModel#requestGetCurriculum(String)
     */
    private List<Actividad> getCurriculum(){
        String dni = getUserDni();
        return TrainerCurriculumModel.getInstance().requestGetCurriculum(dni);
    }

    /**
     * @return Retorna una lista con las actividades que el entrenador no puede hacer
     * @see #getUserDni()
     * @see TrainerCurriculumModel#requestGetActivitysNotInCurriculum(String)
     */
    private List<Actividad> getActivitysNotInCurriculum(){
        String dni = getUserDni();
        return TrainerCurriculumModel.getInstance().requestGetActivitysNotInCurriculum(dni);
    }

    /**
     * Método eliminar curriculum del correspondiente entrenador
     * @see #getUserDni()
     * @see TrainerCurriculumModel#requestClearCurriculum(String)
     */
    private void clearCurriculum(){
        String dni = getUserDni();
        TrainerCurriculumModel.getInstance().requestClearCurriculum(dni);
    }

    /**
     * @return Retorna el dni del usuario del sistema
     * @see Sistema#userSelected#getUserDni()
     */
    private String getUserDni(){
        return Sistema.getInstance().userSelected.getDni();
    }

    /**
     * Método que asigna las actividades que el entrenador puede hacer en su correspondinete lista
     * @see #activitysCurriculum
     * @see TrainerCurriculumModel#requestSetCurriculum(String, Actividad)
     */
    private void setCurriculum(){
        String dni = getUserDni();
        for (Actividad act: activitysCurriculum.getItems()){
            TrainerCurriculumModel.getInstance().requestSetCurriculum(dni, act);
        }

    }

    /**
     * Método que carga los eventos en sus elementos
     * @see #activitysUntrained
     * @see #selectActivityUntrained()
     * @see #activitysCurriculum
     * @see #selectActivityCurriculum()
     * @see #canButton
     * @see #canButtonEvent()
     * @see #cantButton
     * @see #cantButtonEvent()
     * @see #exitButton
     * @see #exitButtonEvent()
     */
    private void loadEvents(){
        activitysUntrained.setOnMouseClicked(event -> selectActivityUntrained());
        activitysCurriculum.setOnMouseClicked(event -> selectActivityCurriculum());
        canButton.setOnAction(event -> canButtonEvent());
        cantButton.setOnAction(event -> cantButtonEvent());
        exitButton.setOnAction(event -> exitButtonEvent());
    }

    /**
     * Método encargado de asignar la actividad seleccionada a la lista de aactividades aptas
     * @see #canBeMoved(List)
     * @see #moveActivity(ListView, ListView)
     */
    private void canButtonEvent(){
        if (canBeMoved(activitysUntrained.getItems())){
            moveActivity(activitysCurriculum, activitysUntrained);
        }
    }

    /**
     * Método encargado de asignar la actividad seleccionada a la lista de aactividades no aptas
     * @see #canBeMoved(List)
     * @see #moveActivity(ListView, ListView)
     */
    private void cantButtonEvent(){
        if (canBeMoved(activitysCurriculum.getItems())){
            moveActivity(activitysUntrained, activitysCurriculum);
        }
    }

    /**
     * Método encargado de actualizar el curriculum
     * @see #clearCurriculum()
     * @see #setCurriculum()
     */
    private void updateCurriculum(){
        clearCurriculum();
        setCurriculum();
    }

    /**
     * Método encargado de cambiar la escena
     * @see Sistema#cambiarEscena(String, int, int)
     * @see #updateCurriculum()
     */
    private void exitButtonEvent(){
        updateCurriculum();
        Sistema.getInstance().cambiarEscena("user-view.fxml", 900, 600);
    }

}
