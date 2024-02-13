package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Esta clase es el repositorio del controlador de lo de borrar usuarios.
 * @author alumne
 * @version java 20
 */
public final class UserDeleteRepository extends RepositorySource {
    private static UserDeleteRepository instance;
    private UserDeleteRepository(){}
    public static UserDeleteRepository getInstance(){
        if (instance == null){
            instance = new UserDeleteRepository();
        }
        return instance;
    }

    /**
     * Método borrar usuario
     * @param dni String
     */
    public void deleteUser(String dni){
        String delete = "DELETE FROM Persona WHERE dni = ?";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(delete)){

            pstmt.setString(1, dni);
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Método borrar emlpeado
     * @param dni String
     */
    public void deleteEmployee(String dni){
        String delete = "DELETE FROM Empleado WHERE dni = ?";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(delete)){

            pstmt.setString(1, dni);
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
