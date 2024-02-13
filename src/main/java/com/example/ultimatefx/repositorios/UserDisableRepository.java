package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Esta clase es el repositorio del controlador de deshabilitar usuarios
 * @author alumne
 * @version java 20
 */
public final class UserDisableRepository extends RepositorySource {
    private static UserDisableRepository instance;
    private UserDisableRepository(){}
    public static UserDisableRepository getInstance(){
        if (instance==null){
            instance = new UserDisableRepository();
        }
        return instance;
    }

    /**
     * Método que deshabilita un usuario
     * @param dni String
     */
    public void setBannedStatusTrue(String dni){
        String update = "UPDATE Persona SET banned = 1 WHERE dni = ?";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(update)){
            pstmt.setString(1, dni);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Método que habilita un usuario
     * @param dni String
     */
    public void setBannedStatusFalse(String dni){
        String update = "UPDATE Persona SET banned = 0 WHERE dni = ?";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(update)){
            pstmt.setString(1, dni);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
