package com.setur.se23.dependency.render.canvas;

import com.setur.se23.engine.render.common.ViewPort;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class CanvasRendererTest {

    private Stage _stageStub;

    @BeforeEach
    void setUp() {
        // we are using mockito to mock the class because the Stage class is not an interface that we can stub
        _stageStub = mock(Stage.class);
    }

    // this can be improved by using parameterized tests, passing in the width and height as parameters, where one is negative and the other is positive for each
    @Test
    void initialize_WithWidthSmallerThan0_ThrowsException() {
        // Arrange
        var invalidWidth = -1;
        var validHeight = 100;
        var canvasRenderer = new CanvasRenderer(_stageStub);

        // Act
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                canvasRenderer.initialize(new ViewPort(invalidWidth, validHeight))
        );

        // Assert
        Assertions.assertNotNull(exception);
    }

    @Test
    void initialize_WithHeightSmallerThan0_ThrowsException() {
        // Arrange
        var invalidWidth = 100;
        var validHeight = -100;
        var canvasRenderer = new CanvasRenderer(_stageStub);

        // Act
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                canvasRenderer.initialize(new ViewPort(invalidWidth, validHeight))
        );

        // Assert
        Assertions.assertNotNull(exception);
    }


}