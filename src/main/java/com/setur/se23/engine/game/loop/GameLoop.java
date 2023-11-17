package com.setur.se23.engine.game.loop;

import com.setur.se23.engine.game.timing.Time;

public class GameLoop {

    private final GameLoopExecutorInterface _initiator;

    public GameLoop(GameLoopExecutorInterface initiator) {
        _initiator = initiator;
    }

    public void start() {
        _initiator.start(this::update);
    }

    private void update(float currentTime) {
        Time.getInstance().updateTime(currentTime);
    }

}
