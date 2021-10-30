package com.example.demo;

import javafx.scene.input.KeyCode;

public class Tester {
    public static void main(String[] args)
    {
        //Code to test if game engine is working
        HelloApplication e = new HelloApplication();

        Draw draw = new Draw(new Vector(), "Jetpack Character.png") {
            @Override
            public void update() {
                if(e.isPressed(KeyCode.P)) {
                    rotation(new Vector(20, 4));
                }
            }
        };
        e.addLayer("Jetpack");
        e.getLayer("Jetpack").add(draw);

        new Thread(e).start();
    }
}
