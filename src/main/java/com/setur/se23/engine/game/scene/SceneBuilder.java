package com.setur.se23.engine.game.scene;

import com.setur.se23.engine.game.GameObject;

import java.util.ArrayList;

public class SceneBuilder {

    private final ArrayList<GameObject> _gameObjects = new ArrayList<>();

    public ArrayList<GameObject> getGameObjects() {
        return _gameObjects;
    }

    public void add(GameObject gameObject) {
        _gameObjects.add(gameObject);
    }
}
