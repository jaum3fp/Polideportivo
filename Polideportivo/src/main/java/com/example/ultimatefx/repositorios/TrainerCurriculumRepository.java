package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase es el repositorio del controlador de entrenador curriculum
 * @author alumne
 * @version java 20
 */
public final class TrainerCurriculumRepository extends RepositorySource {
    private static TrainerCurriculumRepository instance;
    private TrainerCurriculumRepository(){}
    public static TrainerCurriculumRepository getInstance(){
        if (instance == null){
            instance = new TrainerCurriculumRepository();
        }
        return instance;
    }

    /**
     * Método establecer actividad a un empleado
     * @param dni String
     * @param actividad Actividad
     */
    public void setEmpleadoActividad(String dni, Actividad actividad){
        String insert = "INSERT INTO EmpleadoActividades VALUES (?, ?)";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(insert)){
            pstmt.setString(1, dni);
            pstmt.setInt(2, actividad.getId());
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param dni String
     * @return retorna una lista con las actividades del entrenador
     */
    public List<Actividad> getCurriculum(String dni){
        List<Actividad> curriculum = new LinkedList<>();
        String querry = """
                SELECT a.id, a.nombre
                FROM EmpleadoActividades e
                INNER JOIN Actividad a
                ON(e.idActividad=a.id)
                WHERE dniEmpleado = ?
        """;
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(querry)){
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                curriculum.add(
                        new Actividad(id, nombre)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return curriculum;
    }

    /**
     * @param dni String
     * @return retorna una lista con las actividades que el entrenador no puede hacer
     */
    public List<Actividad> getActivitysNotInCurriculum(String dni){
        List<Actividad> actividades = new LinkedList<>();
        String querry = """
                SELECT * FROM Actividad
                WHERE nombre NOT IN (
                SELECT a.nombre
                FROM EmpleadoActividades e
                INNER JOIN Actividad a ON(e.idActividad=a.id)
                WHERE dniEmpleado = ? )
        """;
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(querry)){
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                actividades.add(
                        new Actividad(id, nombre)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return actividades;
    }

    /**
     * Método que elimina un curriculum
     * @param dni String
     */
    public void clearCurriculum(String dni){
        String querry = "DELETE FROM EmpleadoActividades WHERE dniEmpleado = ?";
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(querry)){
            stmt.setString(1, dni);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @return retorna una lista con las actividades
     */
    public List<Actividad> getActividades(){
        List<Actividad> actividades = new LinkedList<>();
        String querry = "SELECT * FROM Actividad";
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry)){
            while (rs.next()){

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                actividades.add(
                        new Actividad(id, nombre)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return actividades;
    }


}
