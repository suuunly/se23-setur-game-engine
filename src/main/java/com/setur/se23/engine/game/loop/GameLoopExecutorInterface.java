package com.setur.se23.engine.game.loop;

public interface GameLoopExecutorInterface {

    void start(GameLoopExecCallback loopExecCallback);

    void stop();
}
