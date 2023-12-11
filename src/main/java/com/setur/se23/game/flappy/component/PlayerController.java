package com.setur.se23.game.flappy.component;

import com.setur.se23.engine.core.math.Transform;
import com.setur.se23.engine.core.math.Vector2D;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.UpdatableComponent;
import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.input.Input;

public class PlayerController extends BaseComponent implements UpdatableComponent, InitializingComponent {
    // some arbitrary game specific properties just to demonstrate the render module
    private static final float SPEED = 100;
    private float _xDir = 1;
    private float _yDir = 0;

    private Transform _transform;

    public PlayerController() {

        Input.listenForEvent("left", () -> {
            _xDir = -1;
            _yDir = 0;
        });

        Input.listenForEvent("right", () -> {
            _xDir = 1;
            _yDir = 0;
        });

        Input.listenForEvent("up", () -> {
            _yDir = -1;
            _xDir = 0;
        });

        Input.listenForEvent("down", () -> {
            _yDir = 1;
            _xDir = 0;
        });
    }

    @Override
    public void start() {
        _transform = getGameObject().transform;
    }

    @Override
    public void update() {
        float deltaTime = Time.deltaTime();
        _transform.translate(new Vector2D(_xDir * SPEED * deltaTime, _yDir * SPEED * deltaTime));
    }
}
