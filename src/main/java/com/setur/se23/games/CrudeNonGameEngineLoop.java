package com.setur.se23.games;

import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import javafx.animation.AnimationTimer;

public class CrudeNonGameEngineLoop extends AnimationTimer {

    // some quick and dirty example material to render a bird
    // note: that the texture should really be loaded through a resource manager
    private final Material material = new Material(
            new Texture2D("file:src/main/resources/sprites/flappy-bird.png", 40, 30),
            new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)
    );
    private long previousTime = 0;

    // some arbitrary x position to show the bird on screen on start
    private double xPos = 10.0;

    public CrudeNonGameEngineLoop() {
        Renderer.getInstance().allocateTexture(material.texture());
    }

    @Override
    public void handle(long currentTime) {
        if (previousTime == 0) {
            previousTime = currentTime;
        }

        // Quick and dirty method of calculating the delta time
        double deltaTimeNs = currentTime - previousTime;
        double deltaTimeS = deltaTimeNs / 1_000_000_000.0;

        // Call your update method to update the game state based on deltaTime
        update(deltaTimeS);

        // Call your render method to render the game state to the screen
        render();

        previousTime = currentTime;
    }

    private void update(double deltaTime) {
        // just some quick example to move the bird slowly to the right
        xPos += 100 * deltaTime;
    }

    private void render() {
        Renderer.getInstance().render(material, xPos, 10.0);

        Renderer.getInstance().swapBuffers();
    }
}
