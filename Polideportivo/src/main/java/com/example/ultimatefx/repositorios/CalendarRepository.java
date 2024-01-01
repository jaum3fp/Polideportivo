package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.dao.HorasReserva;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase es el repositorio del controlador del calendario.
 * @author alumne
 * @version java 20
 */
public final class CalendarRepository extends RepositorySource {
    private static CalendarRepository instance;
    private CalendarRepository(){}
    public static CalendarRepository getInstance(){
        if (instance == null){
            instance = new CalendarRepository();
        }
        return instance;
    }

    /**
     * @return Retorna una lista con las horas de la base de datos
     */
    public List<HorasReserva> getHoras(){
        List<HorasReserva> horas = new ArrayList<>();
        String querry = "SELECT id, hora FROM HorasReserva";
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry);){
                while (rs.next()){
                    int id = rs.getInt("id");
                    String fecha = rs.getString("hora");
                    horas.add(
                            new HorasReserva(id, LocalTime.parse(fecha))
                    );
                }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return horas;
    }

    /**
     * @return retorna las actividades de la base de datos
     */
    public List<Actividad> getActividades(){
        List<Actividad> actividades = new ArrayList<>();
        String querry = "SELECT * FROM Actividad";
        try (Statement stmt = dataSource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(querry)){
            while (rs.next()){
                int dni = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int limite = rs.getInt("limite");
                actividades.add(
                        new Actividad(dni, nombre, limite)
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return actividades;
    }

    /**
     * @return retorna el dni de los usuarios de la base de datos
     */
    public List<String> getDniClientes(){
        List<String> clientes = new LinkedList<>();
        String querry = "SELECT dni FROM Persona WHERE dni NOT IN(SELECT dni from Empleado)";
        try(Statement stmt = dataSource.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(querry)){
            while (rs.next()){
                clientes.add(rs.getString("dni"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    /**
     * @param start principio semana LocalDate
     * @param end fin semana LocalDate
     * @return retorna una lista con las reservas dentro del rango de dias especificado
     */
    public List<Reserva> getReservasSemana(LocalDate start, LocalDate end){
        List<Reserva> reservas = new ArrayList<>();
        String querry = """
                SELECT r.Id, r.cliente, r.fecha, a.nombre, e.nombre, r.empleado
                FROM Actividad a
                INNER JOIN Reserva r on(a.id = r.idActividad)
                INNER JOIN EstadosReserva e on(r.idEstadoReserva = e.id)
                WHERE r.fecha > ? AND r.fecha < ?;
        """;
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(querry)){

            pstmt.setString(1, String.valueOf(start));
            pstmt.setString(2, String.valueOf(end));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){

                int id = rs.getInt("Id");
                String cliente = rs.getString("cliente");
                String fecha = rs.getString("fecha").replace(' ', 'T');
                String nombreAcividad = rs.getString("nombre");
                String nombreEstado = rs.getString("e.nombre");
                String empleado = rs.getString("empleado");

                reservas.add(
                        new Reserva(id, cliente, LocalDateTime.parse(fecha), nombreAcividad, nombreEstado, empleado)
                );
            }
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return reservas;
    }

    /**
     * Método que establece una reserva
     * @param userDni dni usuario String
     * @param fecha fecha String
     * @param actId id int
     */
    public void setReserva(String userDni, String fecha, int actId){
        String insert = "INSERT INTO Reserva VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(insert)){

            pstmt.setInt(1, 0);
            pstmt.setString(2, userDni);
            pstmt.setString(3, fecha);
            pstmt.setInt(4, actId);
            pstmt.setInt(5, 1);
            pstmt.setString(6, null);

            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
