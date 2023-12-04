package com.setur.se23.engine.game.component;

import java.util.ArrayList;

public class ComponentManager {

    private final ArrayList<BaseComponent> _components = new ArrayList<>();

    public <TComponent extends BaseComponent> void addComponent(TComponent component) {

        _components.add(component);
    }

    public ArrayList<BaseComponent> getAllComponents() {
        return _components;
    }

    public <TComponent extends BaseComponent> TComponent getComponent(Class<TComponent> componentClass) {
        for (BaseComponent component : _components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }

        return null;
    }
}
