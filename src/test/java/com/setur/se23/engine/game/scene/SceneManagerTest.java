package com.setur.se23.engine.game.scene;

import com.setur.se23.engine.game.loop.GameIterationInterface;
import com.setur.se23.engine.game.loop.GameLoop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SceneManagerTest {

    SceneManager sut;
    GameLoop loop;

    @BeforeEach()
    void setUp() {
        SceneManager.initialize();
        sut = SceneManager.getInstance();

        loop = new GameLoop(mock(GameIterationInterface.class));
    }

    @AfterEach()
    void tearDown() {
        SceneManager.destroy();
    }


    @Test()
    void addScene_WhenNoSceneExists_AddsSceneToCollection() {

        // Arrange
        Scene scene = new Scene("test", loop);

        // Act
        sut.addScene(scene);

        // Assert
        assertEquals(sut.numberOfScenes(), 1);
    }

    @Test()
    void addScene_WhenSceneAlreadyExists_ThrowsException() {
        // Arrange
        Scene scene = new Scene("test", loop);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> sut.addScene(scene).addScene(scene));
    }

    @Test()
    void addScene_WhenNoSceneIsExists_SetsAddedSceneAsCurrent() {
        // Arrange
        Scene scene = new Scene("test", loop);

        // Act
        sut.addScene(scene);

        // Assert
        assertEquals(sut.getCurrentScene(), scene);
    }

    @Test()
    void getCurrentScene_WhenNoSceneIsSelected_ReturnsNull() {
        // Arrange
        // Act
        // Assert
        assertNull(sut.getCurrentScene());
    }

    @Test()
    void selectScene_WhenSceneExists_SetsSceneAsCurrent() {
        // Arrange
        var sceneName1 = "test1";
        var sceneName2 = "test2";

        sut.addScene(new Scene(sceneName1, loop)).addScene(new Scene(sceneName2, loop));

        // Act
        sut.selectScene(sceneName2);

        // Assert
        assertEquals(sut.getCurrentScene().getName(), sceneName2);
    }

    @Test()
    void selectScene_WhenSelectedSceneDoesNotExist_ThrowsException() {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> sut.selectScene("non-existing-scene-name"));
    }
}