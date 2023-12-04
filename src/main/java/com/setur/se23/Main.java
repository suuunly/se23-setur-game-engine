package com.setur.se23;

import com.setur.se23.dependency.game.JavaFxGameLoop;
import com.setur.se23.dependency.render.canvas.CanvasRenderer;
import com.setur.se23.engine.game.GameObject;
import com.setur.se23.engine.game.component.render.SpriteRenderer;
import com.setur.se23.engine.game.loop.GameLoop;
import com.setur.se23.engine.game.scene.Scene;
import com.setur.se23.engine.game.scene.SceneManager;
import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.ViewPort;
import com.setur.se23.engine.resource.Resources;
import com.setur.se23.engine.resource.parser.MaterialResourceParser;
import com.setur.se23.engine.resource.process.JsonFileProcessor;
import com.setur.se23.game.flappy.component.PlayerController;
import javafx.application.Application;
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

        SceneManager.getInstance().addScene(setupMainScene(stage));
    }

    private Scene setupMainScene(Stage stage) {
        var scene = new Scene("main");

        scene.setupScene(builder -> {

            var bird = new GameObject("bird");
            bird.addComponent(new SpriteRenderer(Resources.load("materials/flappy-bird.mat", Material.class)));
            bird.addComponent(new PlayerController(stage));

            builder.add(bird);
        });

        return scene;
    }

    private void initializeGameEngine(Stage stage) {

        // resource setup
        Resources.initialize(new JsonFileProcessor(), "src/main/resources/")
                .registerParser(new MaterialResourceParser());

        // world setup
        GameLoop.initialize(new JavaFxGameLoop());
        SceneManager.initialize();
        Time.instantiate();

        // renderer setup
        var canvasRenderer = new CanvasRenderer(stage);
        Renderer.Instantiate(canvasRenderer).initialize(new ViewPort(stage.getWidth(), stage.getHeight()));

        GameLoop.getInstance().start();
    }
}