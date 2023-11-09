package com.setur.se23.engine.render;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RendererTest {

    @Test
    void getInstance_NotInstantiated_ReturnsNull() {
        // Arrange
        // Act
        var instance = Renderer.getInstance();

        // Assert
        assertNull(instance);
    }

    @Test
    void instantiate_NotInstantiated_InstantiatesRenderer() {
        // Arrange
        var stub = new RendererPipelineStub();

        // Act
        Renderer.Instantiate(stub);

        // Assert
        assertNotNull(Renderer.getInstance());
    }

    @AfterEach
    void tearDown() {
        Renderer.Destroy();
    }

}