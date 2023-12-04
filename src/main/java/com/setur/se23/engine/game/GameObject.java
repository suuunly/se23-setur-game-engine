package com.setur.se23.engine.game;

import com.setur.se23.engine.core.math.Transform;
import com.setur.se23.engine.core.math.Vector2D;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.ComponentManager;

public class GameObject {

    public final Transform transform = new Transform(Vector2D.zero(), Vector2D.one(), 0);

    public final ComponentManager components = new ComponentManager();
    private final String _name;


    public GameObject(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void initialize() {
        components.getAllComponents().forEach((comp -> comp.initialize(this)));
    }

    public <TComponent extends BaseComponent> void addComponent(TComponent component) {
        components.addComponent(component);
    }

    public <TComponent extends BaseComponent> TComponent getComponent(Class<TComponent> componentClass) {
        return components.getComponent(componentClass);
    }
}
