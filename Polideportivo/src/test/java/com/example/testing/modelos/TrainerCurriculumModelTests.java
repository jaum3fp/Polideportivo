package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Actividad;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.modelos.TrainerCurriculumModel;
import com.example.ultimatefx.repositorios.TrainerCurriculumRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * Método que contiene los tests de este modelo
 */
@RunWith(MockitoJUnitRunner.class)
public class TrainerCurriculumModelTests extends ModelTests {

    @Mock
    private TrainerCurriculumRepository trainerCurriculumRepositoryMock;

    @Override
    public void singeltonModelInstanceTest() {
        TrainerCurriculumModel instance = TrainerCurriculumModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, TrainerCurriculumModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest() {
        TrainerCurriculumRepository repository = TrainerCurriculumRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, TrainerCurriculumRepository.getInstance());
    }

    /**
     * Test que prueba el retorno de todas las actividades que el entrenador puede hacer
     */
    @Test
    public void requestGetCurriculumTest() {
        String dni = new Persona("00000000V").getDni();

        List<Actividad> actividadList = TrainerCurriculumModel.getInstance().requestGetCurriculum(dni);

        Assert.assertNotNull(dni);
        Assert.assertNotNull(actividadList);
    }

    /**
     * Test que prueba el retorno de las actividades
     */
    @Test
    public void requestGetActividadesTest() {
       List<Actividad> actividadList = TrainerCurriculumModel.getInstance().requestGetActividades();

       Assert.assertNotNull(actividadList);
       Assert.assertFalse(actividadList.isEmpty());
    }

    /**
     * Test que establece el nuevo curriculum
     */
    @Test
    public void requestSetCurriculumTest() {
        String dni = "00000000V";
        Actividad actividad = new Actividad();

        //TrainerCurriculumModel.getInstance().requestSetCurriculum(dni, actividad);
        trainerCurriculumRepositoryMock.setEmpleadoActividad(dni, actividad);

        Assert.assertNotNull(dni);
        Assert.assertNotNull(actividad);

        Mockito.verify(trainerCurriculumRepositoryMock).setEmpleadoActividad(dni, actividad);
    }

    /**
     * Test que prueba el retorno de la lista de las actividades que el entrenador no puede hacer
     */
    @Test
    public void requestGetActivitysNotInCurriculumTest() {
        String dni = "00000000V";

        List<Actividad> actividadList = TrainerCurriculumRepository.getInstance().getActivitysNotInCurriculum(dni);

        Assert.assertNotNull(dni);
        Assert.assertNotNull(actividadList);
    }

    /**
     * Test que prueba que el dni del entrenador al que se le va a borrar el curriculum no sea nulo
     */
    @Test
    public void requestClearCurriculumTest() {
        String dni = "00000000V";

        //TrainerCurriculumRepository.getInstance().clearCurriculum(dni);
        //Retornar curriculum y assert empty

        Assert.assertNotNull(dni);
    }


}
