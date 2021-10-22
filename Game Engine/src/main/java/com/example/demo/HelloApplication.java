package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;
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
public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Engine");
        //Create the layout
        Group root = new Group();
        //Create the scene
        Scene scene = new Scene(root, 1500, 700, Color.rgb(226, 125, 96));
         // Creates a line that we can use(starting x, starting y, end x, end y position)
        /*Line line = new Line(350, 30, 360, 30);
        line.setStroke(Color.rgb(36, 37, 130));
        root.getChildren().add(line);*/
        // Create a curve and add it to the scene
        /*QuadCurve curve = new QuadCurve(180, 100, 240, 50, 320, 100);
        curve.setStroke(Color.rgb(141, 135, 65));
        curve.setFill(Color.rgb(5, 56, 107));
        root.getChildren().add(curve); */
        // Create an ellipse and add it to the scene
        /*Ellipse elipse = new Ellipse(250, 300, 30, 70);
        elipse.setStroke(Color.rgb(252, 68, 69));
        elipse.setFill(Color.rgb(142, 228, 175));
        root.getChildren().add(elipse);*/
        // Create a rectangle and add it to the scene
        Rectangle rect = new Rectangle(500, 400, 150, 100);
        rect.setStroke(Color.rgb(93, 92, 97));
        rect.setFill(Color.rgb(46, 17, 20));
        root.getChildren().add(rect);

        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        // Creates the scene
        primaryStage.setScene(scene);
         // comment test //
        final long startNanoTime = System.nanoTime();
         Position p = new Position(15, 15);
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                drawShapes(gc, p);
            }
        }.start();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    private void drawShapes(GraphicsContext gc, Position p) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.fillArc(p.x, p.y, 30, 30, 0, 360, ArcType.OPEN);
        p.x++;
        p.y++;

    }

}