package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.modelos.OfficerAssignReservationModel;
import com.example.ultimatefx.modelos.TrainerCurriculumModel;
import com.example.ultimatefx.repositorios.OfficerAssignReservationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

/**
 * Método que contiene los tests de este modelo
 */
@RunWith(MockitoJUnitRunner.class)
public class OfficerAssignReservationModelTests extends ModelTests {

    // Mock del repositorio de este modelo
    @Mock
    private OfficerAssignReservationRepository officerAssignReservationRepositoryMock;

    @Override
    public void singeltonModelInstanceTest() {
        OfficerAssignReservationModel instance = OfficerAssignReservationModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, OfficerAssignReservationModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest() {
        OfficerAssignReservationRepository repository = OfficerAssignReservationRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, OfficerAssignReservationRepository.getInstance());
    }

    /**
     * Test que prueba que retorna todas las reservas
     */
    @Test
    public void requestGetAllReservasTest(){
        Set<Reserva> reservas = OfficerAssignReservationRepository.getInstance().getAllReservas();

        Assert.assertNotNull(reservas);
    }

    /**
     * Test que prueba que retorna los entrenadores
     */
    @Test
    public void requestGetTrainersTest(){
        Set<Persona> trainers = OfficerAssignReservationRepository.getInstance().getTrainers();

        Assert.assertNotNull(trainers);
    }

    /**
     * Test que prueba que actualiza el estado de una reserva
     */
    @Test
    public void requestSetTrainerStatusTest(){
        String dni = "00000000V";

        officerAssignReservationRepositoryMock.updateReservationTrainerYStatus(dni, 1, 2);

        Assert.assertNotNull(dni);

        Mockito.verify(officerAssignReservationRepositoryMock).updateReservationTrainerYStatus(dni, 1, 2);

    }
}
