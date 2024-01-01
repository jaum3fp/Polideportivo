package com.example.ultimatefx.modelos;

import com.example.ultimatefx.repositorios.UserRegisterRepository;

import java.util.Set;

/**
 * Esta clase es el modelo del controlador de registrar usuario.
 * @author alumne
 * @version java 20
 */
public final class UserRegisterModel {

    private static UserRegisterModel instance;
    private final UserRegisterRepository repository = UserRegisterRepository.getInstance();
    private UserRegisterModel(){}
    public static UserRegisterModel getInstance(){
        if (instance == null){
            instance = new UserRegisterModel();
        }
        return instance;
    }

    /**
     * Método que registra un usuario
     * @param nom nom String
     * @param dni dni String
     * @param passwd nom contraseña String
     */
    public void requestSetUser(String nom, String dni, String passwd){
        repository.setUser(nom, dni, passwd);
    }

    /**
     * Método que registra un empleado
     * @param dni dni empleado String
     * @param type tipo emlpeado int
     */
    public void requestSetEmployee(String dni, int type){
        repository.setEmployee(dni, type);
    }

    /**
     * @return Retorna los dnis de los usuarios
     */
    public Set<String> requestUserDnis(){
        return repository.getUserDnis();
    }
}
