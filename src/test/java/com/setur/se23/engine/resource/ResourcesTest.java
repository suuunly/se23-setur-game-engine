package com.setur.se23.engine.resource;

import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.resource.process.JsonFileProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourcesTest {

    static final String TEST_ROOT = "src/test/resources/";


    @BeforeEach()
    void setUp() {
        Resources.initialize(new JsonFileProcessor(), TEST_ROOT);
    }

    @Test()
    void load_WhenFileDoesNotExist_ThrowsException() {
        // Arrange
        // Act
        // Arrange
        assertThrows(
                RuntimeException.class,
                () -> Resources.load("does-not-exist.txt", Material.class)
        );
    }

    @Test()
    void load_WhenAValidFileIsLoaded_ReturnsMaterial() {
        // Arrange
        // Act
        var material = Resources.load("valid-file.mat", Material.class);

        // Assert
        assertEquals(1.0f, material.colour().r());
    }

    @Test()
    void load_WhenAnInvalidFileIsLoaded_ThrowsException() {
        // Arrange
        // Act
        // Assert
        assertThrows(
                RuntimeException.class,
                () -> Resources.load("invalid-file.mat", Material.class)
        );
    }
}