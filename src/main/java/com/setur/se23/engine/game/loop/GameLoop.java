package com.setur.se23.engine.game.loop;

import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.render.Renderer;

import java.util.ArrayList;

public class GameLoop {

    private static GameLoop _instance;
    private final GameIterationInterface _iteration;
    private final ArrayList<FrameInterface> _frameComputation = new ArrayList<>();

    private GameLoop(GameIterationInterface iteration) {
        _iteration = iteration;
        _iteration.initialize(this::update);
    }

    public static GameLoop getInstance() {
        assert _instance != null;
        return _instance;
    }

    public static void initialize(GameIterationInterface iteration) {
        _instance = new GameLoop(iteration);
    }

    public void subscribeToFrame(FrameInterface frame) {
        _frameComputation.add(frame);
    }

    public void unsubscribeFromFrame(FrameInterface frame) {
        _frameComputation.remove(frame);
    }

    public void start() {
        _iteration.startLoop();
    }

    public void stop() {
        _iteration.stopLoop();
    }

    private void update(float currentTime) {

        Time.getInstance().updateTime(currentTime);

        _frameComputation.forEach(frame -> frame.update(currentTime));

        Renderer.getInstance().swapBuffers();
    }
}
