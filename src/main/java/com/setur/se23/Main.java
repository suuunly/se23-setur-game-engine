package com.setur.se23;

import com.setur.se23.dependency.game.JavaFxGameLoop;
import com.setur.se23.dependency.input.JavaFXInputListener;
import com.setur.se23.dependency.render.canvas.CanvasRenderer;
import com.setur.se23.engine.core.visual.Material;
import com.setur.se23.engine.game.GameObject;
import com.setur.se23.engine.game.component.render.SpriteRenderer;
import com.setur.se23.engine.game.loop.GameLoop;
import com.setur.se23.engine.game.scene.Scene;
import com.setur.se23.engine.game.scene.SceneManager;
import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.ViewPort;
import com.setur.se23.engine.resource.Resources;
import com.setur.se23.engine.resource.process.JsonFileProcessor;
import com.setur.se23.game.flappy.component.PlayerController;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        stage.setWidth(800.0f);
        stage.setHeight(800.0f);
        stage.setTitle("Game Engine Boilerplate!");
        initializeGameEngine(stage);

        SceneManager.getInstance().addScene(setupMainScene());
    }

    private Scene setupMainScene() {
        var scene = new Scene("main");

        scene.setupScene(builder -> {

            var bird = new GameObject("bird");
            bird.addComponent(new SpriteRenderer(Resources.load("materials/flappy-bird", Material.class)));
            bird.addComponent(new PlayerController());

            builder.add(bird);
        });

        return scene;
    }

    private void initializeGameEngine(Stage stage) {

        // input
        var javafxInput = new JavaFXInputListener(stage)
                .addKey(KeyCode.W, "up")
                .addKey(KeyCode.A, "left")
                .addKey(KeyCode.S, "down")
                .addKey(KeyCode.D, "right");
        Input.initialize(javafxInput);

        // resource setup
        Resources.initialize(new JsonFileProcessor(), "src/main/resources/");

        // world setup
        GameLoop.initialize(new JavaFxGameLoop());
        SceneManager.initialize();
        Time.instantiate();

        // renderer setup
        var canvasRenderer = new CanvasRenderer(stage);
        Renderer.Instantiate(canvasRenderer).initialize(new ViewPort(stage.getWidth(), stage.getHeight()));

        Input.startListener();

        GameLoop.getInstance().start();
    }
}