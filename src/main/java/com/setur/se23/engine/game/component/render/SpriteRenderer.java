package com.setur.se23.engine.game.component.render;

import com.setur.se23.engine.core.math.Vector2D;
import com.setur.se23.engine.game.component.BaseComponent;
import com.setur.se23.engine.game.component.behaviour.InitializingComponent;
import com.setur.se23.engine.game.component.behaviour.RenderableComponent;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.Material;

/**
 * The SpriteRenderer class is responsible for rendering a sprite using a specified material.
 */
public class SpriteRenderer extends BaseComponent implements RenderableComponent, InitializingComponent {

    private Material _material;

    /**
     * Constructs a new SpriteRenderer with the specified material.
     *
     * @param material The material used to render the sprite.
     */
    public SpriteRenderer(Material material) {
        _material = material;
    }

    @Override
    public void start() {
        Renderer.getInstance().allocateTexture(_material.texture());
    }

    /**
     * Gets the material used by the sprite renderer.
     *
     * @return The material used to render the sprite.
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * Sets the material used by the sprite renderer.
     *
     * @param material The new material to be used for rendering the sprite.
     */
    public void setMaterial(Material material) {
        _material = material;
    }

    @Override
    public void render() {
        Vector2D position = getGameObject().transform.position();
        Renderer.getInstance().render(_material, position.x(), position.y());
    }
}
