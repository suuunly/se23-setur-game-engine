package com.setur.se23.engine.game;

import com.setur.se23.engine.core.math.Transform;
import com.setur.se23.engine.core.math.Vector2D;
import com.setur.se23.engine.game.component.ComponentInterface;
import com.setur.se23.engine.game.component.ComponentManager;

public class GameObject {

    public final Transform transform = new Transform(Vector2D.zero(), Vector2D.one(), 0);

    public final ComponentManager components = new ComponentManager();

    public void initialize() {
        components.getAllComponents().forEach((comp -> {
            comp.initialize(this);
            comp.start();
        }));
    }

    public void update() {
        components.getAllComponents().forEach(ComponentInterface::update);
    }
}
