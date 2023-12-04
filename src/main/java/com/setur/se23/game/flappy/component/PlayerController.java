package com.setur.se23.game.flappy.component;

import com.setur.se23.engine.core.math.Transform;
import com.setur.se23.engine.core.math.Vector2D;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.UpdatableComponent;
import com.setur.se23.engine.game.timing.Time;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class PlayerController extends BaseComponent implements UpdatableComponent, InitializingComponent {
    // some arbitrary game specific properties just to demonstrate the render module
    private static final float SPEED = 100;
    private float _xDir = 1;
    private float _yDir = 0;

    private Transform _transform;

    public PlayerController(Stage stage) {
        // note: this code should be abstract behind an input management module (just like the render module)
        // again, the input system should not be aware of stage/scene. That should be abstracted away just like the render module.
        stage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.A) {
                _xDir = -1;
                _yDir = 0;
            } else if (code == KeyCode.D) {
                _xDir = 1;
                _yDir = 0;
            }

            if (code == KeyCode.W) {
                _yDir = -1;
                _xDir = 0;
            } else if (code == KeyCode.S) {
                _yDir = 1;
                _xDir = 0;
            }
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
