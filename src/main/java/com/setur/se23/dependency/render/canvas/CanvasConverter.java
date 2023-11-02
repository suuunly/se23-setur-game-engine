package com.setur.se23.dependency.render.canvas;

import com.setur.se23.engine.render.common.MaterialColour;
import javafx.scene.paint.Color;

public class CanvasConverter {
    public static Color ToFxColor(MaterialColour color) {
        return new Color(
                color.r(),
                color.g(),
                color.b(),
                color.a()
        );
    }
}
