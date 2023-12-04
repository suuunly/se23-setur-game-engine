package com.setur.se23.engine.game.scene;

import com.setur.se23.engine.game.GameObject;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.RenderableComponent;
import com.setur.se23.engine.game.component.behaviour.UpdatableComponent;
import com.setur.se23.engine.game.loop.GameLoop;

import java.util.ArrayList;

public class Scene {

    private final String _sceneName;
    private final ArrayList<GameObject> _gameObjects = new ArrayList<>();

    private final GameLoop _loop;

    public Scene(String name, GameLoop loop) {
        _sceneName = name;
        _loop = loop;
    }

    public String getName() {
        return _sceneName;
    }

    public void addGameObject(GameObject gameObject) {

        _gameObjects.add(gameObject);

        var components = gameObject.components.getAllComponents();

        // todo: this should be part of the game loop, not the scene
        for (BaseComponent component : components) {

            if (component instanceof InitializingComponent) {
                _loop.subscribeToStart((InitializingComponent) component);
            }

            if (component instanceof UpdatableComponent) {
                _loop.subscribeToUpdate((UpdatableComponent) component);
            }

            if (component instanceof RenderableComponent) {
                _loop.subscribeToRender((RenderableComponent) component);
            }
        }

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
            }
        }
    }

    public void start() {
        _loop.start();
    }

    public void stop() {
        _loop.stop();
    }

}
