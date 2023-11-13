package com.setur.se23;

import com.setur.se23.engine.game.timing.Time;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

// Note: DISCLAIMER: THIS CLASS IS PURELY HERE TO DEMONSTRATE A QUICK DEMO OF SOMETHING MOVING ON THE SCREEN OVER TIME.
// YOU CAN TAKE INSPIRATION FROM THIS CLASS ON HOW YOU COULD IMPLEMENT YOUR GAME LOOP
// HOWEVER, BEAR IN MIND THAT THE LOOP SHOULD NOT KNOW OF ANYTHING RELATED TO JAVAFX AND ESPECIALLY NOT ANY GAME SPECIFIC LOGIC
public class CrudeNonGameEngineLoop extends AnimationTimer {

    // some arbitrary game specific properties just to demonstrate the render module
    private static final double SPEED = 100;

    // some quick and dirty example material to render a bird
    // note: that the texture should really be loaded through a resource manager
    private final Material material = new Material(
            new Texture2D("file:src/main/resources/sprites/flappy-bird.png", 40, 30),
            new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)
    );

    // some arbitrary game specific properties just to demonstrate the render module
    private double _xPos = 10.0;
    private double _yPos = 10.0;
    private double _xDir = 1;
    private double _yDir = 0;

    // note: when you build your game loop, do not have any association with anything related to JavaFX.
    // The game loop should not have any hard dependency on JavaFX. It should only use abstractions
    public CrudeNonGameEngineLoop(Stage stage) {

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

        Renderer.getInstance().allocateTexture(material.texture());
    }

    @Override
    public void handle(long currentTime) {

        Time.getInstance().updateTime(currentTime);

        update();

        // Call your render method to render the game state to the screen
        render();
    }

    private void update() {

        float deltaTime = Time.getInstance().getDeltaTime();

        // just some quick example to move the bird in the direction you chose
        _xPos += _xDir * SPEED * deltaTime;
        _yPos += _yDir * SPEED * deltaTime;
    }

    private void render() {

        // just manually rendering the bird
        // note: this should be abstract into a render component of sorts.
        Renderer.getInstance().render(material, _xPos, _yPos);

        Renderer.getInstance().swapBuffers();
    }
}
