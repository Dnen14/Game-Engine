package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;
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

class Position {
    public int x;
    public int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class HelloApplication extends Application implements Runnable {
    private GraphicsContext ctx;
    private int width;
    private int height;
    private static Map<String, List<Drawable>> layers = new LinkedHashMap<>();
    private static HashMap<String, Image> textures = new HashMap<>();
    private static HashSet<KeyCode> keysPressed = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        width = 1500;
        height = 700;
        primaryStage.setTitle("Game Engine");
        //Create the layout
        Group root = new Group();

        //Create the scene
        Scene scene = new Scene(root, 1500, 700, Color.rgb(226, 125, 96));

        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        // Creates the scene
        primaryStage.setScene(scene);

        // comment test
        // AnimationTimer()
        final long startNanoTime = System.nanoTime();
        Position p = new Position(15, 15);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                draw();
            }
        }.start();
        primaryStage.show();

    }


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