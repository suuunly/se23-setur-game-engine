package com.setur.se23.dependency.loop.animationTimer;

import com.setur.se23.engine.game.loop.GameLoopExecCallback;
import com.setur.se23.engine.game.loop.GameLoopExecutorInterface;
import javafx.animation.AnimationTimer;

public class AnimationTimerGameLoop extends AnimationTimer implements GameLoopExecutorInterface {

    private GameLoopExecCallback _loopExecCallback;

    @Override
    public void start(GameLoopExecCallback loopExecCallback) {
        _loopExecCallback = loopExecCallback;
        super.start();
    }

    @Override
    public void handle(long l) {
        _loopExecCallback.execute(l);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
