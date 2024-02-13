package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.dao.HorasReserva;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.modelos.CalendarModel;
import com.example.ultimatefx.repositorios.CalendarRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

/**
 * Método que contiene los tests de este modelo
 */
@RunWith(MockitoJUnitRunner.class)
public class CalendarModelTests extends ModelTests {

    // Mock del repositorio de este modelo
    @Mock
    private CalendarRepository calendarRepositoryMock;

    @Override
    public void singeltonModelInstanceTest() {
        CalendarModel instance = CalendarModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, CalendarModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest(){
        CalendarRepository repository = CalendarRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, CalendarRepository.getInstance());
    }

    /**
     * Test que prueba el retorno de la lista de horas
     */
    @Test
    public void requestGetHorasTest(){
        List<HorasReserva> listaHoras = CalendarRepository.getInstance().getHoras();

        Assert.assertNotNull(listaHoras);
        Assert.assertFalse(listaHoras.isEmpty());
    }

    /**
     * Test que prueba el retorno de la lista de actividades
     */
    @Test
    public void requestGetActividadesTest(){
        List<HorasReserva> listaActividades = CalendarRepository.getInstance().getHoras();

        Assert.assertNotNull(listaActividades);
        Assert.assertFalse(listaActividades.isEmpty());
    }

    /**
     * Test que prueba el retorno de la lista de reservas
     */
    @Test
    public void requestGetReservasSemanaTest(){
        LocalDate start = LocalDate.of(2023, 6, 5);
        LocalDate end = LocalDate.of(2023, 6, 9);
        List<Reserva> listaReservas = CalendarRepository.getInstance().getReservasSemana(start, end);

        Assert.assertNotNull(start);
        Assert.assertNotNull(end);
        Assert.assertNotNull(listaReservas);
    }

    /**
     * Test que prueba una reserva
     */
    @Test
    public void requestSetReservaTest(){
        String clienteDni = "00000000V";
        String dateHour = "2023-02-22T12:00:00";

        calendarRepositoryMock.setReserva(clienteDni, dateHour, 0);

        Assert.assertNotNull(clienteDni);
        Assert.assertNotNull(dateHour);

        Mockito.verify(calendarRepositoryMock).setReserva(clienteDni, dateHour, 0);

    }

    /**
     * Test que prueba el retorno de la lista de dnis de los clientes
     */
    @Test
    public void requestGetDniClientesTest(){
        List<String> listaDnis = CalendarRepository.getInstance().getDniClientes();

        Assert.assertNotNull(listaDnis);
    }
}
