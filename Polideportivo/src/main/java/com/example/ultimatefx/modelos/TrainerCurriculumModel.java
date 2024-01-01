package com.example.ultimatefx.modelos;

import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.repositorios.TrainerCurriculumRepository;

import java.util.List;

/**
 * Esta clase es el modelo del controlador del curriculum del entrenador.
 * @author alumne
 * @version java 20
 */
public final class TrainerCurriculumModel {
    private static TrainerCurriculumModel instance;
    private final TrainerCurriculumRepository repository = TrainerCurriculumRepository.getInstance();
    private TrainerCurriculumModel(){}
    public static TrainerCurriculumModel getInstance(){
        if (instance == null){
            instance = new TrainerCurriculumModel();
        }
        return instance;
    }

    /**
     * @param dni dni trainer String
     * @return Retorna una lista con todas las actividades que el entrenador puede hacer
     */
    public List<Actividad> requestGetCurriculum(String dni){
        return repository.getCurriculum(dni);
    }

    /**
     * @return Retorna una lista con todas las actividades
     */
    public List<Actividad> requestGetActividades(){
        return repository.getActividades();
    }

    /**
     * Establece el curriculum del entrenador
     * @param dni dni String
     * @param actividad actividad Actividad
     */
    public void requestSetCurriculum(String dni, Actividad actividad){
        repository.setEmpleadoActividad(dni, actividad);
    }

    /**
     * @param dni dni String
     * @return Retorna una lista con las actividades que el entrenador no puede hacer
     */
    public List<Actividad> requestGetActivitysNotInCurriculum(String dni){
        return repository.getActivitysNotInCurriculum(dni);
    }

    /**
     * Método que elimina un curriculum
     * @param dni dni entrenador String
     */
    public void requestClearCurriculum(String dni){
        repository.clearCurriculum(dni);
    }
}
