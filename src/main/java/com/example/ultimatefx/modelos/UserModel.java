package com.example.ultimatefx.modelos;

import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.UserRepository;

import java.util.Set;

/**
 * Esta clase es el modelo del controlador de las funciones de un usuario.
 * @author alumne
 * @version java 20
 */
public final class UserModel {
    private static UserModel instance;
    private final UserRepository repository = UserRepository.getInstance();
    private UserModel(){}
    public static UserModel getInstance(){
        if (instance == null){
            instance = new UserModel();
        }
        return instance;
    }

    /**
     * @return Retorna las reservas de un usuario
     */
    public Set<Reserva> requestReservations(){
        return repository.getReservations();
    }
}
