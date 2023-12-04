package com.setur.se23.dependency.render.canvas;

import com.setur.se23.engine.core.visual.Material;
import com.setur.se23.engine.core.visual.Texture2D;
import com.setur.se23.engine.render.BufferItem;
import com.setur.se23.engine.render.RenderPipelineInterface;
import com.setur.se23.engine.render.ViewPort;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CanvasRenderer implements RenderPipelineInterface {

    private final Stage _stage;

    // note: this should be extended to be a double-buffer. Meaning that whilst one buffer is being filled,
    // the other is being rendered, and vice versa
    private final List<BufferItem> _buffer = new ArrayList<>();

    private final HashMap<String, Image> _textureMap = new HashMap<>();

    private Canvas _canvas;

    public CanvasRenderer(Stage stage) {
        _stage = stage;
    }

    @Override
    public void initialize(ViewPort viewport) throws IllegalArgumentException {

        double width = viewport.width();
        double height = viewport.height();

        if (width < 0.0) {
            throw new IllegalArgumentException("Width must be larger than 0. Received: " + width);
        }
        if (height < 0.0) {
            throw new IllegalArgumentException("Height must be larger than 0. Received: " + height);
        }

        _canvas = new Canvas(width, height);

        var group = new Group(_canvas);
        var scene = new Scene(group, 320, 240);
        _stage.setScene(scene);
        _stage.show();
    }

    @Override
    public void allocateTexture(Texture2D texture) {
        // todo: convert this to a pixel based solution instead so we don't need to resort to a string based solution
        _textureMap.put(texture.path(), new Image("file:src/main/resources/" + texture.path()));
    }

    @Override
    public void swapBuffers() {
        var context = _canvas.getGraphicsContext2D();

        // Clears the entire screen
        context.clearRect(0, 0, _canvas.getHeight(), _canvas.getWidth());

        // iterates over all the requested render items, and pushes them onto the canvas
        for (var item : _buffer) {
            var materialColour = item.material().colour();
            var texture = item.material().texture();

            // note: this color should be used to tint the image
            Color color = CanvasConverter.ToFxColor(materialColour);

            Image img = _textureMap.get(texture.path());

            context.drawImage(img, item.x(), item.y(), texture.width(), texture.height());
        }

        // clears the buffer to make it ready for the next render pass.
        _buffer.clear();
    }

    @Override
    public void render(Material material, double x, double y) {
        _buffer.add(new BufferItem(material, x, y));
    }
}
