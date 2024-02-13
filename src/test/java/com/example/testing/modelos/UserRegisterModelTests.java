package com.example.testing.modelos;


import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.modelos.UserRegisterModel;
import com.example.ultimatefx.repositorios.UserRegisterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Método que contiene los tests de este modelo
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRegisterModelTests extends ModelTests {

    // Mock del repositorio de este modelo
    @Mock
    private UserRegisterRepository userRegisterRepositoryMocked;

    @Override
    public void singeltonModelInstanceTest() {
        UserRegisterModel instance = UserRegisterModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, UserRegisterModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest() {
        UserRegisterRepository repository = UserRegisterRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, UserRegisterRepository.getInstance());
    }

    /**
     * Test que prueba la inserción de un usuario en la base de datos
     */
    @Test
    public void requestSetUserTest(){
        Set<String> dbDni = new HashSet<>();
        String nom = "POKEMON";
        String dni = "00000000A";
        dbDni.add(dni);
        String passwd = "asdf";

        Mockito.when(userRegisterRepositoryMocked.getUserDnis()).thenReturn(dbDni);

        userRegisterRepositoryMocked.setUser(nom, dni, passwd);
        Set<String> dnis = userRegisterRepositoryMocked.getUserDnis();

        Assert.assertNotNull(nom);
        Assert.assertNotNull(dni);
        Assert.assertNotNull(passwd);
        Assert.assertNotNull(dnis);
        Assert.assertTrue(dnis.contains(dni));

        Mockito.verify(userRegisterRepositoryMocked).setUser(nom, dni, passwd);
        Mockito.verify(userRegisterRepositoryMocked).getUserDnis();

    }

    /**
     * Test que prueba la inserción de un empleado en una base de datos
     */
    @Test
    public void requestSetEmployeeTest(){
        String dni = "88888888D";
        int type = 1;

        userRegisterRepositoryMocked.setEmployee(dni, type);

        Assert.assertNotNull(dni);
        Assert.assertEquals(type, 1);

        Mockito.verify(userRegisterRepositoryMocked).setEmployee(dni, type);

    }

    /**
     * Test que prueba que se retorne una lista con los dni de los usuarios la cual no puede estar vacía
     */
    @Test
    public void requestUserDniTest(){
        Set<String> dniList = UserRegisterRepository.getInstance().getUserDnis();

        Assert.assertNotNull(dniList);
        Assert.assertFalse(dniList.isEmpty());
    }

}
