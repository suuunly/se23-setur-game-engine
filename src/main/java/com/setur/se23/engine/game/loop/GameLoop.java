package com.setur.se23.engine.game.loop;

import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.RenderableComponent;
import com.setur.se23.engine.game.component.behaviour.UpdatableComponent;
import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.render.Renderer;

import java.util.ArrayList;

public class GameLoop {

    private final GameIterationInterface _iteration;

    private final ArrayList<InitializingComponent> _starts = new ArrayList<>();

    private final ArrayList<UpdatableComponent> _updates = new ArrayList<>();
    private final ArrayList<RenderableComponent> _renders = new ArrayList<>();

    public GameLoop(GameIterationInterface iteration) {
        _iteration = iteration;

        _iteration.initialize(this::update);
    }

    public void subscribeToUpdate(UpdatableComponent component) {
        _updates.add(component);
    }

    public void subscribeToRender(RenderableComponent component) {
        _renders.add(component);
    }

    public void subscribeToStart(InitializingComponent component) {
        _starts.add(component);
    }

    public void start() {
        for (InitializingComponent start : _starts) {
            start.start();
        }

        _iteration.startLoop();
    }

    public void stop() {
        _iteration.stopLoop();
    }

    private void update(float currentTime) {
        Time.getInstance().updateTime(currentTime);

        for (UpdatableComponent update : _updates) {
            update.update();
        }

        for (RenderableComponent render : _renders) {
            render.render();
        }

        Renderer.getInstance().swapBuffers();
    }
}
