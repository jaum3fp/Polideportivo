package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.modelos.LoginModel;
import com.example.ultimatefx.repositorios.LoginRepository;
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
public class LoginModelTests extends ModelTests {

    // Mock del repositorio de este modelo
    @Mock
    private LoginRepository loginRepositoryMock;

    @Override
    public void singeltonModelInstanceTest() {
        LoginModel instance = LoginModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, LoginModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest(){
        LoginRepository repository = LoginRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, LoginRepository.getInstance());
    }

    /**
     * Test que prueba el login de un usuarios
     */
    @Test
    public void requestLoginTest(){
        String user = "12345678A";
        String passwd = "felix";
        //Persona userLoged = LoginRepository.getInstance().getUser(user, passwd);

        Mockito.when(loginRepositoryMock.getUser(user, passwd)).thenReturn(new Persona("Username", user, passwd, ""));

        Persona userLoged = loginRepositoryMock.getUser(user, passwd);

        Assert.assertNotNull(user);
        Assert.assertNotNull(passwd);
        Assert.assertNotNull(userLoged);
        Assert.assertEquals(userLoged.getDni(), user);
        Assert.assertEquals(userLoged.getPassword(), passwd);

        Mockito.verify(loginRepositoryMock).getUser(user, passwd);
    }

    /**
     * Test que prueba que la persona no sea nula
     */
    @Test
    public void userIsNullTest(){
        Persona personal = new Persona("", "", "", "");
        Persona personaNull = null;

        Assert.assertNull(personaNull);
        Assert.assertNotNull(personal);
    }
}
