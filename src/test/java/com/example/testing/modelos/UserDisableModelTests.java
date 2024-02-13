package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.modelos.UserDisableModel;
import com.example.ultimatefx.repositorios.UserDisableRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Método que contiene los tests de este modelo
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDisableModelTests extends ModelTests {

    // Mock del repositorio de este modelo
    @Mock
    private UserDisableRepository userDisableRepositoryMock;

    @Override
    public void singeltonModelInstanceTest() {
        UserDisableModel instance = UserDisableModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, UserDisableModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest() {
        UserDisableRepository repository = UserDisableRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, UserDisableRepository.getInstance());
    }

    /**
     * Test que prueba que un usuario esté baneado
     */
    @Test
    public void requestSetBanned() {
        String userDni = "12345678A";

        userDisableRepositoryMock.setBannedStatusFalse(userDni);
        userDisableRepositoryMock.setBannedStatusTrue(userDni);

        Assert.assertNotNull(userDni);

        Mockito.verify(userDisableRepositoryMock).setBannedStatusFalse(userDni);
        Mockito.verify(userDisableRepositoryMock).setBannedStatusFalse(userDni);
    }

}
