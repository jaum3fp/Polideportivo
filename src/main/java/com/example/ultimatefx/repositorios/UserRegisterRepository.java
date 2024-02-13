package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase es el repositorio del controlador del registro de usuarios
 * @author alumne
 * @version java 20
 */
public final class UserRegisterRepository extends RepositorySource {
    private static UserRegisterRepository instance;
    private UserRegisterRepository(){}
    public static UserRegisterRepository getInstance(){
        if (instance == null){
            instance = new UserRegisterRepository();
        }
        return instance;
    }


    /**
     * @return lista de usuarios dni
     */
    public Set<String> getUserDnis(){
        Set<String> userDnis = new HashSet<>();
        String querry = "SELECT dni FROM Persona";
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry)){
            while (rs.next()){
                userDnis.add(rs.getString("dni"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userDnis;
    }

    /**
     * Método que inserta un usuario
     * @param nom String
     * @param dni String
     * @param passwd String
     */
    public void setUser(String nom, String dni, String passwd){
        String insert = "INSERT INTO Persona VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(insert)){

            pstmt.setString(1, nom);
            pstmt.setString(2, dni);
            pstmt.setString(3, passwd);
            pstmt.setBoolean(4, false);

            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Método que inserta un empleado
     * @param dni String
     * @param type int
     */
    public void setEmployee(String dni, int type){
        String insert = "INSERT INTO Empleado VALUES (?, ?)";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(insert)){

            pstmt.setString(1, dni);
            pstmt.setInt(2, type);

            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
