package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase es el repositorio del controlador del login.
 * @author alumne
 * @version java 20
 */
public final class LoginRepository extends RepositorySource {
    private static LoginRepository instance;
    private LoginRepository(){}
    public static LoginRepository getInstance(){
        if (instance == null){
            instance = new LoginRepository();
        }
        return instance;
    }

    /**
     * @param dni dni usuario a logear String
     * @param passwd contraseña usuario String
     * @return Retorna un objeto Persona que coincide con los datos proporcionados
     */
    public Persona getUser(String dni, String passwd){
        Persona person = null;
        String querry = """
                SELECT * FROM (
                SELECT p.nombre, p.dni, p.password, t.tipo, p.banned
                FROM Persona p
                NATURAL JOIN Empleado e
                INNER JOIN TipoEmpleado t
                ON(e.idTipoEmpleado=t.Id)
                UNION
                SELECT p.nombre, p.dni, p.password, NULL AS tipo, p.banned
                FROM Persona p
                WHERE dni NOT IN(select dni from Empleado)
                ) AS data
                WHERE data.dni = ? AND data.password = ?;
        """;
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(querry)){

            pstmt.setString(1, dni);
            pstmt.setString(2, passwd);

            ResultSet rs = pstmt.executeQuery();

            String nom = null;
            String tipo = null;
            boolean isBanned = false;

            while (rs.next()){

                nom = rs.getString("nombre");
                if (rs.getString("tipo")==null){
                    tipo = "";
                } else {
                    tipo = rs.getString("tipo");
                }
                isBanned = rs.getBoolean("banned");

            }

            person = new Persona(nom, dni, passwd, tipo, isBanned);

            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return person;
    }
}
