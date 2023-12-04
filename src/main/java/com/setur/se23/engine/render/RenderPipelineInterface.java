package com.setur.se23.engine.render;

import com.setur.se23.engine.core.visual.Material;
import com.setur.se23.engine.core.visual.Texture2D;

public interface RenderPipelineInterface {

    void initialize(ViewPort viewport);

    /**
     * Allocates a texture into the render pipeline, to process it for rendering.
     *
     * @param texture The texture to allocate
     */
    void allocateTexture(Texture2D texture);

    void swapBuffers();

    void render(Material material, double x, double y);
}
