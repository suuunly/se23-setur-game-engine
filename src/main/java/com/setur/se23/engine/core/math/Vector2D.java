package com.setur.se23.engine.core.math;

public record Vector2D(float x, float y) {

    public static Vector2D zero() {
        return new Vector2D(0, 0);
    }

    public static Vector2D one() {
        return new Vector2D(1, 1);
    }

    public Vector2D add(Vector2D displacement) {
        return new Vector2D(x + displacement.x, y + displacement.y);
    }
}
