package com.example.ultimatefx.modelos;

import com.example.ultimatefx.repositorios.UserDisableRepository;

/**
 * Esta clase es el modelo del controlador de deshabilitar usuarios
 * @author alumne
 * @version java 20
 */
public final class UserDisableModel {
    private static UserDisableModel instance;
    private final UserDisableRepository repository = UserDisableRepository.getInstance();
    private UserDisableModel(){}
    public static UserDisableModel getInstance(){
        if (instance==null){
            instance = new UserDisableModel();
        }
        return instance;
    }

    /**
     * Habilira o deshabilita usuarios
     * @param isBanned estado usuario boolean
     * @param dni dni usuario String
     */
    public void requestSetBanned(boolean isBanned, String dni){
        if (isBanned){
            repository.setBannedStatusTrue(dni);
        } else {
            repository.setBannedStatusFalse(dni);
        }
    }
}
