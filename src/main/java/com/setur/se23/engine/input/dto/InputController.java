package com.setur.se23.engine.input.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputController {

    private final Map<String, ArrayList<InputCallback>> _callbacks = new HashMap<>();

    public InputController() {

    }

    public void add(String key, InputCallback callback) {

        if (_callbacks.containsKey(key)) {
            _callbacks.get(key).add(callback);
            return;
        }

        ArrayList<InputCallback> callbacks = new ArrayList<>();
        callbacks.add(callback);

        _callbacks.put(key, callbacks);
    }

    public ArrayList<InputCallback> get(String key) {
        ArrayList<InputCallback> callbacks = _callbacks.get(key);
        if (callbacks == null) {
            return new ArrayList<>();
        }

        return callbacks;
    }

}
