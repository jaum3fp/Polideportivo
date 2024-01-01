package com.example.testing.modelos.template;

import org.junit.Test;

/**
 * Método que contiene métodos abstractos comunes entre modelos
 */
public abstract class ModelTests {
    /**
     * Test de la instancia del método
     */
    @Test
    public abstract void singeltonModelInstanceTest();

    /**
     * Test de la instancia del repositorio
     */
    @Test
    public abstract void repositoryInstanceTest();
}
