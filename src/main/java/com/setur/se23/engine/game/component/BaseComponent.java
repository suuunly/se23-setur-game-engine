package com.setur.se23.engine.game.component;

import com.setur.se23.engine.game.GameObject;

public abstract class BaseComponent implements ComponentInterface {

    private GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    public void initialize(GameObject owner) {
        this.gameObject = owner;
    }
}
