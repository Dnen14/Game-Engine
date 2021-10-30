package com.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
//Drawable object
public interface Drawable {
    void draw(GraphicsContext gc, HashMap<String, Image> textures);
}
