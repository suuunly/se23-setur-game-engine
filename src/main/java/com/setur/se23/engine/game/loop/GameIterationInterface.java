package com.setur.se23.engine.game.loop;

public interface GameIterationInterface {

    void initialize(FrameInterface frameCallback);

    void startLoop();

    void stopLoop();
}
