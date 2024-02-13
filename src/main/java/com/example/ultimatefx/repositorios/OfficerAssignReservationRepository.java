package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Esta clase es el repositorio del controlador de la asignacion de las reservas.
 * @author alumne
 * @version java 20
 */
public final class OfficerAssignReservationRepository extends RepositorySource {

    private static OfficerAssignReservationRepository instance;
    private OfficerAssignReservationRepository(){}
    public static OfficerAssignReservationRepository getInstance(){
        if (instance == null){
            instance = new OfficerAssignReservationRepository();
        }
        return instance;
    }

    /**
     * @return retorna una lista con todas las reservas pendientes
     */
    public Set<Reserva> getAllReservas(){
        Set<Reserva> reservas = new LinkedHashSet<>();
        String querry ="""
                SELECT r.Id, r.cliente, r.fecha, a.nombre, r.empleado
                FROM Actividad a
                INNER JOIN Reserva r on(a.id = r.idActividad)
                WHERE r.idEstadoReserva = 1
        """;
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry)){
            while(rs.next()){

                int id = rs.getInt("Id");
                String cliente = rs.getString("cliente");
                String fecha = rs.getString("fecha").replace(' ', 'T');
                String nombreEstado = rs.getString("nombre");
                String empleado = rs.getString("empleado");

                reservas.add(
                        new Reserva(id, cliente, LocalDateTime.parse(fecha), nombreEstado, empleado)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservas;
    }

    /**
     * @return retorna una lista de entrenadores
     */
    public Set<Persona> getTrainers(){
        Set<Persona> reservas = new LinkedHashSet<>();
        String querry ="SELECT dni FROM Empleado WHERE idTipoEmpleado = 3";
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry)){
            while(rs.next()){

                String dni = rs.getString("dni");

                reservas.add(
                        new Persona(dni)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservas;
    }

    /**
     * Método que actualiza el estado de las reservas
     * @param dni dni String
     * @param id id int
     * @param status status int
     */
    public void updateReservationTrainerYStatus(String dni, int id, int status){
        String update ="UPDATE Reserva SET idEstadoReserva = ?, empleado = ? WHERE Id = ?";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(update)){

            pstmt.setInt(1, status);
            pstmt.setString(2, dni);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param actividad actividad String
     * @param dni dni String
     * @return retorna si el entrenador está capacitado
     */
    public boolean trainerIsTrained(String actividad, String dni){
        boolean isTrained = false;
        String select = """
                SELECT a.nombre
                FROM EmpleadoActividades e 
                INNER JOIN Actividad a on(e.idActividad=a.id) 
                WHERE e.dniEmpleado = ? AND a.nombre = ?        
        """;
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(select)){

            pstmt.setString(1, dni);
            pstmt.setString(2, actividad);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String act = rs.getString("nombre");
                if (actividad.equals(act))
                    isTrained = true;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return isTrained;
    }

}
