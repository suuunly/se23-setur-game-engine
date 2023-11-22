package com.setur.se23.engine.core.math;

public class Transform {

    private Vector2D _position;
    private Vector2D _scale;
    private float _rotation;

    public Transform(Vector2D position, Vector2D scale, float rotation) {
        _position = position;
        _scale = scale;
        _rotation = rotation;
    }

    public Vector2D position() {
        return _position;
    }

    public void translate(Vector2D displacement) {
        this._position = _position.add(displacement);
    }
}
