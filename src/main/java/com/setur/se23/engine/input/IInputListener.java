package com.setur.se23.engine.input;

import com.setur.se23.engine.input.dto.InputController;

public interface IInputListener {

    void initialize(InputController controller);

    void terminate();
}
