package com.setur.se23.dependency.input;

import com.setur.se23.engine.input.IInputListener;
import com.setur.se23.engine.input.dto.InputCallback;
import com.setur.se23.engine.input.dto.InputController;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaFXInputListener implements IInputListener {

    private final Stage _stage;
    private final HashMap<KeyCode, String> _keyMap = new HashMap<>();
    private InputController _controller;

    public JavaFXInputListener(Stage stage) {
        _stage = stage;
    }

    public JavaFXInputListener addKey(KeyCode keyCode, String keyName) {
        _keyMap.put(keyCode, keyName);
        return this;
    }

    @Override
    public void initialize(InputController controller) {
        _controller = controller;

        _stage.getScene().setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            String keyName = _keyMap.get(keyCode);
            if (keyName == null) {
                return;
            }

            ArrayList<InputCallback> callback = _controller.get(keyName);
            for (InputCallback inputCallback : callback) {
                inputCallback.triggerEvent();
            }
        });
    }

    @Override
    public void terminate() {
        // todo: terminate listener
    }
}
