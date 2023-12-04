package com.setur.se23.engine.game.scene;

import com.setur.se23.engine.game.GameObject;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.RenderableComponent;
import com.setur.se23.engine.game.component.behaviour.UpdatableComponent;
import com.setur.se23.engine.game.loop.GameLoop;

import java.util.ArrayList;

public class Scene {

    private final ArrayList<InitializingComponent> _starts = new ArrayList<>();
    private final ArrayList<UpdatableComponent> _updates = new ArrayList<>();
    private final ArrayList<RenderableComponent> _renders = new ArrayList<>();

    private final String _sceneName;
    private ArrayList<GameObject> _gameObjects = new ArrayList<>();

    public Scene(String name) {
        _sceneName = name;
    }

    public void setupScene(SetupScene setup) {

        var builder = new SceneBuilder();
        setup.setup(builder);

        _gameObjects = builder.getGameObjects();
    }

    public String getName() {
        return _sceneName;
    }

    public GameObject findObjectWithName(String name) {
        for (GameObject gameObject : _gameObjects) {
            if (gameObject.getName().equals(name)) {
                return gameObject;
            }
        }
        return null;
    }

    public void initialize() {
        for (GameObject gameObject : _gameObjects) {
            ArrayList<BaseComponent> components = gameObject.components.getAllComponents();

            for (BaseComponent component : components) {
                
                component.initialize(gameObject);

                if (component instanceof InitializingComponent) {
                    _starts.add((InitializingComponent) component);
                }

                if (component instanceof UpdatableComponent) {
                    _updates.add((UpdatableComponent) component);
                }

                if (component instanceof RenderableComponent) {
                    _renders.add((RenderableComponent) component);
                }
            }
        }
    }

    public void start() {
        for (InitializingComponent start : _starts) {
            start.start();
        }

        GameLoop.getInstance().subscribeToFrame(this::computeFrame);
    }

    public void stop() {
        _starts.clear();
        _updates.clear();
        _renders.clear();

        GameLoop.getInstance().unsubscribeFromFrame(this::computeFrame);
    }


    private void computeFrame(float currentTime) {

        for (UpdatableComponent update : _updates) {
            update.update();
        }

        for (RenderableComponent render : _renders) {
            render.render();
        }
    }
}
