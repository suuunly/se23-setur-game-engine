package com.setur.se23.engine.input;

import com.setur.se23.engine.input.dto.InputCallback;
import com.setur.se23.engine.input.dto.InputController;

public class Input {
    private static Input _instance;
    private final IInputListener _listener;
    private final InputController controller = new InputController();

    public Input(IInputListener listener) {
        this._listener = listener;
    }

    public static Input getInstance() {
        assert _instance != null;
        return _instance;
    }

    public static void initialize(IInputListener listener) {
        _instance = new Input(listener);
    }

    public static void startListener() {
        getInstance()._listener.initialize(getInstance().controller);
    }

    public static void stopListener() {
        getInstance()._listener.terminate();
    }

    public static void listenForEvent(String eventName, InputCallback callback) {
        getInstance().controller.add(eventName, callback);
    }

    public void stopListeningForEvent(String eventName) {
        // todo: stop listening for event
    }
}
