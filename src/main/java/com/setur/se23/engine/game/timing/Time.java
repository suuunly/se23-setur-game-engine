package com.setur.se23.engine.game.timing;

import com.setur.se23.engine.render.Renderer;

public class Time {

    private float _timeScale = 1.0f;
    private float _previousTime = 0.0f;

    private float _deltaTime = 0.0f;

    public static Time instance;

    public static void instantiate() {
        synchronized (Renderer.class) {
            if (instance == null) {
                instance = new Time();
            }
        }
    }

    public static void Destroy() {
        instance = null;
    }

    /**
     * Gets the singleton instance of the timer.
     *
     * @return The singleton instance of the timer.
     */
    public static Time getInstance() {
        return instance;
    }

    public static float deltaTime() {
        return getInstance().getDeltaTime();
    }

    public float getDeltaTime() {
        return _deltaTime;
    }

    public void setTimeScale(float timeScale) {
        _timeScale = timeScale;
    }

    public void updateTime(float time) {
        if(_previousTime == 0.0f) {
            _previousTime = time;
            return;
        }

        float deltaTimeNs = time - _previousTime;

        // converts delta time in ns to seconds.
        _deltaTime = nanoSecondsToSeconds(deltaTimeNs) * _timeScale;

        _previousTime = time;
    }

    // todo: move this method to the core module.
    private static float nanoSecondsToSeconds(float nanoSeconds) {
        return nanoSeconds / 1_000_000_000.0f;
    }
}
