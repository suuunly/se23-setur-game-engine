package com.setur.se23.dependency.render.canvas;

import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.ViewPort;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasRendererTest {

    private Stage _stageStub;

    @BeforeEach
    void setUp() {
        _stageStub = new Stage();
    }

    @Test
    void initialize_WithWidthSmallerThan0_ThrowsException() {
        // Arrange
        var invalidWidth = -1;
        var validHeight = 100;
        var canvasRenderer = new CanvasRenderer(_stageStub);

        // Act
        Throwable exception = assertThrows(ArithmeticException.class, () -> {
            canvasRenderer.initialize(new ViewPort(invalidWidth, validHeight));
        });

        // Assert
        assertNotNull(exception);
    }


}