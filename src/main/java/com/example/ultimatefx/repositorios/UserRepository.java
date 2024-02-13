package com.example.ultimatefx.repositorios;

import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.repositorios.connection.RepositorySource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Esta clase es el repositorio del controlador del usuario
 * @author alumne
 * @version java 20
 */
public final class UserRepository extends RepositorySource {
    private static UserRepository instance;
    private UserRepository(){}
    public static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    /**
     * @return retorna todas las reservas disponibles del usuario
     */
    public Set<Reserva> getReservations(){
        Set<Reserva> reservas = new LinkedHashSet<>();
        String querry = """
                SELECT r.Id, r.cliente, r.fecha, a.nombre, e.nombre, r.empleado
                FROM Actividad a
                INNER JOIN Reserva r
                ON(a.id = r.idActividad)
                INNER JOIN EstadosReserva e
                ON(r.idEstadoReserva = e.id)
                WHERE cliente = ?;
        """;
        try (PreparedStatement pstmt = dataSource.getConnection().prepareStatement(querry)) {

            pstmt.setString(1, Sistema.getInstance().userSelected.getDni());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){

                int id = rs.getInt("Id");
                String cliente = rs.getString("cliente");
                LocalDateTime fecha = LocalDateTime.parse(rs.getString("fecha").replace(' ', 'T'));
                String nombreActividad = rs.getString("a.nombre");
                String nombreEstado = rs.getString("e.nombre");
                String empleado = rs.getString("empleado");

                reservas.add(
                        new Reserva(id, cliente, fecha, nombreActividad, nombreEstado, empleado)
                );
            }

            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return reservas;
    }
}
