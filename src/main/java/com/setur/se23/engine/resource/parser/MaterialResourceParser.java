package com.setur.se23.engine.resource.parser;

import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.engine.resource.ProcessedFileContent;
import com.setur.se23.engine.resource.ResourceParser;

public class MaterialResourceParser implements ResourceParser {

    private final static float MAX_COLOUR_VALUE = 255.0f;

    @Override
    public String getExtension() {
        return "mat";
    }

    @Override
    public Object parse(ProcessedFileContent content) {
        var texture = new Texture2D(
                content.get("texture", String.class),
                content.get("width", Integer.class),
                content.get("height", Integer.class)
        );

        var materialColour = new MaterialColour(
                MAX_COLOUR_VALUE / content.get("red", Integer.class),
                MAX_COLOUR_VALUE / content.get("green", Integer.class),
                MAX_COLOUR_VALUE / content.get("blue", Integer.class),
                MAX_COLOUR_VALUE / content.get("alpha", Integer.class)
        );

        return new Material(
                texture,
                materialColour
        );
    }
}
