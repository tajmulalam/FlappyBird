package com.sumon.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sumon on 5/5/2017.
 */

public class Bird {
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap= Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);/// place gravity valu u want to move
        velocity.scl(dt);/// scal with time to perfom
        position.add(MOVEMENT * dt, velocity.y, 0);/// place velocity to the positon to execute
        if (position.y < 0)
            velocity.y = 0;
        velocity.scl(1 / dt); /// return stable velocity postion
        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jump() {
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
