package com.setur.se23.engine.core.visual;

// note: this could be extended to take a byte[] array of pixel data instead of a path
public record Texture2D(String path, int width, int height) {
}
