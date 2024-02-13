package com.example.ultimatefx.modelos;

import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.repositorios.LoginRepository;

/**
 * Esta clase es el modelo del controlador del login.
 * @author alumne
 * @version java 20
 */
public final class LoginModel {
    private static LoginModel instance;
    private final LoginRepository repository = LoginRepository.getInstance();
    private LoginModel(){}
    public static LoginModel getInstance(){
        if (instance == null){
            instance = new LoginModel();
        }
        return instance;
    }

    /**
     * @param userdni userdni String
     * @param passwd contraseña String
     * @return Retorna un objeto persona de la base de datos en caso de que los datos sean correctos
     */
    public Persona userdataRequest(String userdni, String passwd){

        Persona user = repository.getUser(userdni, passwd);

        if (userIsNull(user)){
            return null;
        } else {
            return user;
        }

    }

    /**
     * @param user Usuario a validar
     * @return Nulo en caso de que el test sea erroneo
     */
    private boolean userIsNull(Persona user){
        return user.getNombre() == null;
    }

}
