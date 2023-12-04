package com.setur.se23.engine.game.scene;

import java.util.HashMap;

public class SceneManager {

    private static SceneManager _instance;
    private final HashMap<String, Scene> _scenes = new HashMap<>();
    private Scene _currentScene;

    public static void initialize() {
        _instance = new SceneManager();
    }

    public static void destroy() {
        _instance = null;
    }

    public static SceneManager getInstance() {
        assert _instance != null;
        return _instance;
    }

    public SceneManager addScene(Scene scene) {

        String nameOfScene = scene.getName();

        if (_scenes.containsKey(nameOfScene))
            throw new IllegalArgumentException("Scene with name " + nameOfScene + " already exists");

        _scenes.put(nameOfScene, scene);

        if (numberOfScenes() < 2) {
            selectScene(nameOfScene);
        }

        return this;
    }

    public void selectScene(String name) {
        if (!_scenes.containsKey(name))
            throw new IllegalArgumentException("Scene with name " + name + " does not exist");

        if (_currentScene != null) {
            _currentScene.stop();
        }

        _currentScene = _scenes.get(name);

        _currentScene.initialize();
        _currentScene.start();
    }

    public int numberOfScenes() {
        return _scenes.size();
    }

    public Scene getCurrentScene() {
        return _currentScene;
    }
}
