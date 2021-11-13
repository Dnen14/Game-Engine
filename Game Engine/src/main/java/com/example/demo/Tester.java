package com.example.demo;

import javafx.scene.input.KeyCode;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import static org.jbox2d.common.MathUtils.cos;

public class Tester {
    public static void main(String[] args)
    {
        //Code to test if game engine is working
        HelloApplication e = new HelloApplication();
         /*Player player = new Player(new Vector(), "JetPack Character.png") {
           @Override
           public void update()
           {
              move();
           }
        };*/
        /*Draw draw = new Draw(new Vector(), "Jetpack Character.png") {
            @Override
            public void update() {

            }
        }; */

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5, 5, new Vec2(), 0);
        BodyDef b = new BodyDef();
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.5f;
        fd.friction = 0.03f;
        b.type = BodyType.DYNAMIC;
        Body body = e.addBody(b);
        body.createFixture(fd);

        Player player = new Player(body, "JetPack Character.png") {
            @Override
            public void update()
            {
                body.setGravityScale(body.getPosition().y);
                if(e.isPressed(KeyCode.W))
                {
                    Vec2 v = new Vec2(0, -(float)Math.cos(body.getAngle()));
                    v = v.mul(10000f);
                    body.applyForce( v, new Vec2());
                }
                if(e.isPressed(KeyCode.A))
                {
                    body.setAngularVelocity(-100);
                }
                if(e.isPressed(KeyCode.S))
                {

                    Vec2 v = new Vec2(0, (float)Math.cos(body.getAngle()));
                    v = v.mul(10000f);
                    body.applyForce( v, new Vec2());
                }
                if(e.isPressed(KeyCode.D))
                {
                    body.setAngularVelocity(100);

                }
                if(!(e.isPressed(KeyCode.D)) && !(e.isPressed(KeyCode.A)))
                {
                    body.setAngularVelocity(0);
                }
            }
        };
        e.addLayer("Jetpack");
        e.getLayer("Jetpack").add(player);

        new Thread(e).start();
    }
}
