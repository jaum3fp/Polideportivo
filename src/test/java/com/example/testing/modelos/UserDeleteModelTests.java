package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.modelos.UserDeleteModel;
import com.example.ultimatefx.modelos.UserDisableModel;
import com.example.ultimatefx.repositorios.UserDeleteRepository;
import com.example.ultimatefx.repositorios.UserDisableRepository;
import org.junit.Assert;
import org.junit.Test;

/**
 * Método que contiene los tests de este modelo
 */
public class UserDeleteModelTests extends ModelTests {

    @Override
    public void singeltonModelInstanceTest() {
        UserDeleteModel instance = UserDeleteModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, UserDeleteModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest() {
        UserDeleteRepository repository = UserDeleteRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, UserDeleteRepository.getInstance());
    }

    /**
     * Test que prueba que el dni del usuario a borrar no sea nulo
     */
    @Test
    public void requestDeleteUser(){
        String dni = "12345678A";

        Assert.assertNotNull(dni);
    }
}
