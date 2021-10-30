package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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


public class HelloApplication extends Application implements Runnable {
    private GraphicsContext ctx;
    private int width;
    private int height;
    private static Map<String, List<Drawable>> layers = new LinkedHashMap<>();
    private static HashMap<String, Image> textures = new HashMap<>();
    private static HashSet<KeyCode> keysPressed = new HashSet<>();


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
            public void handle(long currentNanoTime) {
                draw();
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