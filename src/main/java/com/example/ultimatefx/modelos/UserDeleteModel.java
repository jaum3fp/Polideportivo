package com.example.ultimatefx.modelos;

import com.example.ultimatefx.repositorios.UserDeleteRepository;
import com.example.ultimatefx.repositorios.UserDisableRepository;

/**
 * Esta clase es el modelo del controlador de eliminar usuarios.
 * @author alumne
 * @version java 20
 */
public final class UserDeleteModel {
    private static UserDeleteModel instance;
    private final UserDeleteRepository repository = UserDeleteRepository.getInstance();
    private UserDeleteModel(){}
    public static UserDeleteModel getInstance(){
        if (instance == null){
            instance = new UserDeleteModel();
        }
        return instance;
    }

    /**
     * Elimina un usuario y un trabajador en su caso
     * @param dni Dni usuario String
     */
    public void requestDeleteUser(String dni){
        repository.deleteUser(dni);
        repository.deleteEmployee(dni);
    }

}
