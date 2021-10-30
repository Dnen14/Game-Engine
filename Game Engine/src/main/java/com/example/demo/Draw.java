package com.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public abstract class Draw implements Drawable {
    private Vector position;
    private String texture;

    public Draw(Vector position, String texture){
        this.position = position;
        this.texture = texture;
    }

    public Draw(String texture){
        position = new Vector();
        this.texture = texture;
    }

    public void translate(Vector transform)
    {
        position.x += transform.x;
        position.y += transform.y;
    }

    public void rotation(Vector rotate)
    {
       position.x += ((rotate.x * Math.cos(30)) - (rotate.y * Math.sin(30)));
       position.y += ((rotate.x * Math.sin(30)) + (rotate.y * Math.cos(30)));
    }
    public abstract void update();

    @Override
    public void draw(GraphicsContext gc, HashMap<String, Image> textures) {
        update();
        if(!textures.containsKey(texture)) {
            textures.put(texture, new Image(texture));
        }
        gc.drawImage(textures.get(texture), position.x, position.y);
    }
}
