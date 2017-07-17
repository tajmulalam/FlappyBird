package com.sumon.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sumon.flappybird.FlappyBird;

/**
 * Created by Sumon on 5/4/2017.
 */

public class GameOverState extends State {
    private Texture bg;
    private Texture gameOverTexture;

    public GameOverState(GameSateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        gameOverTexture = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        /// need to open sb first
        spriteBatch.begin();
        spriteBatch.draw(bg, 0, 0);
        spriteBatch.draw(gameOverTexture, (cam.position.x) - (gameOverTexture.getWidth() / 2), cam.position.y);
        spriteBatch.end();
    }

    @Override
    protected void dispose() {
        bg.dispose();
        gameOverTexture.dispose();

    }

}
