package com.example.testing.modelos;

import com.example.testing.modelos.template.ModelTests;
import com.example.ultimatefx.controlador.system.Sistema;
import com.example.ultimatefx.dao.Persona;
import com.example.ultimatefx.dao.Reserva;
import com.example.ultimatefx.modelos.UserModel;
import com.example.ultimatefx.modelos.UserRegisterModel;
import com.example.ultimatefx.repositorios.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Método que contiene los tests de este modelo
 */
public class UserModelTests extends ModelTests {

    @Override
    public void singeltonModelInstanceTest() {
        UserModel instance = UserModel.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertSame(instance, UserModel.getInstance());
    }

    @Override
    public void repositoryInstanceTest(){
        UserRepository repository = UserRepository.getInstance();

        Assert.assertNotNull(repository);
        Assert.assertSame(repository, UserRepository.getInstance());
    }

    /**
     * Test que prueba que se retornan correctamente las reservas de un usuario
     */
    @Test
    public void requestReservations(){
        Persona userLoged = Sistema.getInstance().userSelected = new Persona("12345678A");
        Set<Reserva> listaReservas = UserRepository.getInstance().getReservations();

        Assert.assertNotNull(userLoged);
        Assert.assertNotNull(listaReservas);
    }

}
