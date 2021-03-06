package com.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import org.jbox2d.dynamics.Body;

import java.util.HashMap;

public abstract class Draw implements Drawable {
    private Body position;
    private String texture;
    private int width;
    private int height;

    public Draw(Body position, String texture){
        this.position = position;
        this.texture = texture;
        width = 40;
        height = 40;
    }

    public Draw(String texture){
        this.texture = texture;
    }

//    public void translate(Vector transform)
//    {
//        position.x += transform.x;
//        position.y += transform.y;
//    }
    public abstract void update();

    @Override
    public void draw(GraphicsContext gc, HashMap<String, Image> textures) {
        update();
        if(!textures.containsKey(texture)) {
            textures.put(texture, new Image(texture));
        }
        gc.save();
        Rotate r = new Rotate(position.getAngle(), position.getPosition().x + width / 2, position.getPosition().y + height / 2);
        gc.setTransform(r.getMxx(),r.getMyx(),r.getMxy(),r.getMyy(),r.getTx(),r.getTy());
        gc.drawImage(textures.get(texture), position.getPosition().x, position.getPosition().y ,width,height);
        gc.restore();
    }
}
