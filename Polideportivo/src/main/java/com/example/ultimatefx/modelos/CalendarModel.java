package com.example.ultimatefx.modelos;

import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.dao.HorasReserva;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.CalendarRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Esta clase es el modelo del controlador del calendario.
 * @author alumne
 * @version java 20
 */
public final class CalendarModel {
    private static CalendarModel instance;
    private final CalendarRepository repository = CalendarRepository.getInstance();
    private CalendarModel(){}
    public static CalendarModel getInstance(){
        if (instance == null){
            instance = new CalendarModel();
        }
        return instance;
    }

    /**
     * @return Retorna una lista con la información proporcionada por el repositorio
     * @see #repository
     */
    public List<HorasReserva> requestGetHoras(){
        return repository.getHoras();
    }

    /**
     * @return Retorna una lista con la información proporcionada por el repositorio
     * @see #repository
     */
    public List<Actividad> requestGetActividades(){
        return repository.getActividades();
    }

    /**
     * @return Retorna una lista con la información proporcionada por el repositorio
     * @see #repository
     */
    public List<Reserva> requestGetReservasSemana(LocalDate start, LocalDate end){
        return repository.getReservasSemana(start, end);
    }

    /**
     * Pide al repositorio insertar los siguientes datos
     * @see #repository
     */
    public void requestSetReserva(String clientDni, String dateHour, int actId){
        repository.setReserva(clientDni, dateHour, actId);
    }

    /**
     * @return Retorna una lista con la información proporcionada por el repositorio
     * @see #repository
     */
    public List<String> requestGetDniClientes(){
        return repository.getDniClientes();
    }
}
