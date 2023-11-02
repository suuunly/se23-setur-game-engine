package com.setur.se23.games;

import com.setur.se23.dependency.render.canvas.CanvasRenderer;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.ViewPort;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        stage.setWidth(800.0f);
        stage.setHeight(800.0f);
        stage.setTitle("Game Engine Boilerplate!");

        initializeRenderer(stage);

        CrudeNonGameEngineLoop loop = new CrudeNonGameEngineLoop();
        loop.start();
    }

    private void initializeRenderer(Stage stage) {
        var canvasRenderer = new CanvasRenderer(stage);

        Renderer.Instantiate(canvasRenderer)
                .initialize(new ViewPort(stage.getWidth(), stage.getHeight()));
    }
}