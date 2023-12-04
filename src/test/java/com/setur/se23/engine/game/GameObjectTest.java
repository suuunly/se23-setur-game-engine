package com.setur.se23.engine.game;

import com.setur.se23.engine.game.component.BaseComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameObjectTest {

    private GameObject sut;

    @BeforeEach()
    void setUp() {
        sut = new GameObject("TestObject");
    }

    @Test()
    void getName_WhenCreatingAGameObject_NameIsGivenToGameObject() {

        // Arrange
        var gameObjectName = "TestObject";
        var sut = new GameObject(gameObjectName);

        // Act
        var result = sut.getName();

        // Assert
        assertEquals(gameObjectName, result);
    }

    @Test()
    void getComponent_WithMissingComponents_ReturnsNull() {

        // Arrange
        var component = mock(BaseComponent.class);

        // Act
        var result = sut.getComponent(component.getClass());

        // Assert
        assertNull(result);
    }

    @Test()
    void getComponent_WithAnAddedComponent_ReturnsAddedComponent() {
        // Arrange
        var component = mock(BaseComponent.class);
        sut.addComponent(component);

        // Act
        var result = sut.getComponent(component.getClass());

        // Assert
        assertNotNull(result);
        assertEquals(component, result);
    }
}