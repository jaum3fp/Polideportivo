package com.example.ultimatefx.modelos;

import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.OfficerAssignReservationRepository;

import java.util.Set;

/**
 * Esta clase es el modelo del controlador de la asignacion de reservas del oficinista.
 * @author alumne
 * @version java 20
 */
public final class OfficerAssignReservationModel {
    private static OfficerAssignReservationModel instance;
    private final OfficerAssignReservationRepository repository = OfficerAssignReservationRepository.getInstance();
    private OfficerAssignReservationModel(){}
    public static OfficerAssignReservationModel getInstance(){
        if (instance == null){
            instance = new OfficerAssignReservationModel();
        }
        return instance;
    }

    /**
     * @return Retorna las reservas cuyo estado es pendiente
     */
    public Set<Reserva> requestGetAllReservas(){
        return repository.getAllReservas();
    }

    /**
     * @return Retorna el dni de los entrenadores
     */
    public Set<Persona> requestGetTrainers(){
        return repository.getTrainers();
    }

    /**
     * Actualiza el estado de la reserva
     * @param dni dni Entrenador String
     * @param id id actividad String
     * @param status 2 0 3, depende int
     */
    public void requestSetReservationStatus(String dni, int id, int status){
        repository.updateReservationTrainerYStatus(dni, id, status);
    }

    /**
     * @param actividad nom actividad String
     * @param dni dni trainer String
     * @return Retorna si el entrenador está capacitado
     */
    public boolean requestTrainerIsTrained(String actividad, String dni){
        return repository.trainerIsTrained(actividad, dni);
    }
}
