package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;


public class HelloApplication extends Application implements Runnable {
    private GraphicsContext ctx;
    private int width;
    private int height;
    private static Map<String, List<Drawable>> layers = new LinkedHashMap<>();
    private static HashMap<String, Image> textures = new HashMap<>();
    private static HashSet<KeyCode> keysPressed = new HashSet<>();
    private static World world = new World(new Vec2());
    private Vec2 v = new Vec2();


    public HelloApplication()
    {
        width = 1500;
        height = 700;
    }
    @Override
    public void start(Stage primaryStage) {
        //Main Game engine code
        Canvas canvas = new Canvas(width, height);
        ctx = canvas.getGraphicsContext2D();
        //main Render loop
        new AnimationTimer() {
            private long lasttime;
            @Override
            public void start()
            {
                lasttime = System.nanoTime();
                super.start();
            }
            public void handle(long currentNanoTime) {
                long delta = currentNanoTime - lasttime;
                world.step(1f/60f, 3, 5);
                draw();
                lasttime = currentNanoTime;
            }
        }.start();

        primaryStage.setTitle("Game Engine");

        Pane root = new Pane();

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));

        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

        root.getChildren().add(canvas);


        primaryStage.setScene(scene);


        primaryStage.show();

    }

    public boolean isPressed(KeyCode key) {
        return keysPressed.contains(key);
    }

     //Add a layer to the scene
    public void addLayer(String name) {
        layers.put(name, new ArrayList<>());
    }
    // Get the list of layers
    public List<Drawable> getLayer(String layer) {
        return layers.get(layer);
    }

    public Body addBody(BodyDef def)
    {
        return  world.createBody(def);
    }

    //main draw function
    private void draw() {
        ctx.clearRect(0, 0, width, height);
        for (String layerName : layers.keySet()) {
            List<Drawable> layer = layers.get(layerName);
            for (Drawable d : layer) {
                d.draw(ctx, textures);
            }
        }
    }

    @Override
    public void run() {
        launch();
    }
}