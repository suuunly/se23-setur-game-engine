package com.setur.se23.dependency.game;

import com.setur.se23.engine.game.loop.FrameInterface;
import com.setur.se23.engine.game.loop.GameIterationInterface;
import javafx.animation.AnimationTimer;

public class JavaFxGameLoop extends AnimationTimer implements GameIterationInterface {

    private FrameInterface _frameCallback;

    @Override
    public void handle(long currentTime) {
        _frameCallback.update((float) currentTime);
    }

    @Override
    public void initialize(FrameInterface frameCallback) {
        _frameCallback = frameCallback;
    }

    @Override
    public void startLoop() {
        start();
    }

    @Override
    public void stopLoop() {
        stop();
    }

}
